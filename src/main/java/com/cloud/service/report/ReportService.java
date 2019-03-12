package com.cloud.service.report;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cloud.common.excption.BusinessException;
import com.cloud.common.qo.ReportQo;
import com.cloud.common.utils.FileUtil;
import com.cloud.common.utils.ObjectUtils;
import com.cloud.common.utils.UserUtils;
import com.cloud.common.vo.report.ReportDetailVo;
import com.cloud.common.vo.report.ReportVo;
import com.cloud.entity.datacollection.CollectionBatch;
import com.cloud.entity.report.Report;
import com.cloud.entity.report.ReportVisitRecord;
import com.cloud.entity.sys.OrgInfo;
import com.cloud.mapper.master.datacollection.CollectionBatchMapper;
import com.cloud.mapper.master.report.ReportMapper;
import com.cloud.service.sys.OrgInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报告Service
 *
 * @author huangYl
 * @date 2018/11/15
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportService {

    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private AsyncExecutorNewReportDataService newReportDataService;

    @Autowired
    private ReportVisitRecordService visitRecordService;

    @Autowired
    private ReportVcFactory reportVcFactory;

    @Autowired
    private OrgInfoService orgInfoService;

    @Autowired
    private NewReportStatisticsService newReportStatisticsService;

    @Autowired
    private CollectionBatchMapper collectionBatchMapper;

    @Value("${report_detail_path}")
    private String reportDetailPath;

    /**
     * 生成报告
     *
     * @param report 报告实体
     * @author huangYl
     * @date 2018/11/19 10:34
     */
    public void save(Report report) {
        reportMapper.insertSelective(report);
    }

    /**
     * 根据ID查询
     *
     * @param id 报告id
     * @return com.cloud.entity.report.Report
     * @author huangYl
     * @date 2018/11/19 10:35
     */
    public Report getById(Long id) {
        return reportMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询报告分页列表
     *
     * @param qo 查询对象
     * @return PageInfo
     * @author huangYl
     * @date 2018/11/16 10:25
     */
    public PageInfo queryPage(ReportQo qo) {
        //查询登录用户的机构编码，并设置（查询数据根据权限查询，只能看到当前机构以及下级机构的信息）
        String orgCode = UserUtils.getInstance().getCurrentlyUserInfo().getOrgCode();
        qo.setOrgCode(orgCode);
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        List<ReportVo> list = reportMapper.queryPage(qo);
        // 查询内存中是否存在，如果有访问次数优先用内存的
        reportVcFactory.getInstance().verifyReportListByMemory(list);
        return new PageInfo(list);
    }

    /**
     * 生成报告
     *
     * @param id 报告id
     * @return java.util.Map
     * @author huangYl
     * @date 2018/11/16
     */
    public String generateReport(Long id) {
        if (null == id) {
            throw new BusinessException("参数id不能为空");
        }
        Report report = reportMapper.selectByPrimaryKey(id);
        CollectionBatch collectionBatch = collectionBatchMapper.selectByPrimaryKey(report.getBatchId());
        // TODO 这里需要判断生成什么的报告，目前只有迎新报告
        // 判断模板字段数量是否变化，抛错，设置报告状态为生成失败（暂时没有这种情况，数据采集时已经控制了）
        if (report.getGenerateAgain() == 0 && Report.REPORT_STATUS_SUCCESS.equals(report.getGenerateStatus())) {
            throw new BusinessException("该报告已经生成，并且没有新数据，请确认你的操作是否正确");
        }
        boolean repNominated = Report.REPORT_STATUS_NOMINATED.equals(report.getGenerateStatus());
        boolean repGenerateAgain = report.getGenerateAgain() == 1 && Report.REPORT_STATUS_SUCCESS.equals(report.getGenerateStatus());
        if (repNominated || repGenerateAgain) {
            // 判断该报告的批次是否存在有效数据
            if (collectionBatch.getValidNumber() == 0) {
                throw new BusinessException("该批次不存在有效数据，请再次确认");
            }
            // 更新报告状态为生成中
            reportMapper.updateStatus(report.getId(), Report.REPORT_STATUS_GENERATING);
            // 异步统计报告数据，入库
            newReportDataService.asyncStatisticReportData(report);
        }
        String returnMsg = "正在生成报告，请稍后...";
        return returnMsg;
    }

    /**
     * 根据ID查询报告详情
     *
     * @param reportId 报告id
     * @param clientIp 客户端IP
     * @return java.util.Map
     * @author huangYl
     * @date 2018/11/19 19:14
     */
    public Map<String, Object> getReportDetailById(Long reportId, String clientIp) {
        List<ReportDetailVo> list = reportMapper.getReportDetailById(reportId);
        Map<String, Object> result = new HashMap(10);
        for (ReportDetailVo detailVo : list) {
            String value = detailVo.getValue();
            if (result.containsKey(detailVo.getName())) {
                if (ObjectUtils.isNotEmptyString(value)) {
                    result.replace(detailVo.getName(), JSON.parse(value));
                }
            } else {
                if (ObjectUtils.isNotEmptyString(value)) {
                    result.put(detailVo.getName(), JSON.parse(value));
                }
            }
        }
        // 记录客户端IP，统计报告访问次数
        selectCountReportVisits(reportId, clientIp);
        return result;
    }

    /**
     * 异步统计报告详情访问次数
     *
     * @param reportId 报告id
     * @param clientIp 客户端ip
     * @author huangYl
     * @date 2018/11/29 11:40
     */
    @Async
    public void selectCountReportVisits(Long reportId, String clientIp) {
        reportVcFactory.getInstance().selectCountReportVisits(reportId, clientIp);
    }

    /**
     * 异步更新报告访问IP统计
     *
     * @param reportId 报告id
     * @param clientIp 客户端IP
     * @return void
     * @author huangYl
     * @date 2018/11/29 16:54
     */
    @Async
    public void updateReportIpRecord(Long reportId, String clientIp) {
        // 保存IP记录
        visitRecordService.save(new ReportVisitRecord(reportId, clientIp));
    }

    /**
     * 根据报告ID更新访问次数
     *
     * @param id    报告ID
     * @param count 访问次数
     * @author huangYl
     * @date 2018/11/29 19:44
     */
    public void updateVisitCountById(Long id, Integer count) {
        reportMapper.updateVisitCountById(id, count);
    }

    /**
     * 用于定时器从数据库读取数据更新内存中的数据，和更新报告中的访问次数
     *
     * @author huangYl
     * @date 2018/11/29 9:47
     */
    public void updateCacheDataAndReportVisitCount() {
        reportVcFactory.getInstance().updateCacheDataAndReportVisitCount();
    }


    /**
     * 将报告详情保存到json文件中 路径/report/组织机构号/报告id
     *
     * @param reportId 报告ID
     * @param clientIp 客户端IP
     * @return java.util.Map
     * @author huangYl
     * @date 2018/11/28 17:06
     */
    public String getReportToJson(Long reportId, String clientIp) {
        Map<String, Object> result = getReportDetailById(reportId, clientIp);
        result.put("reportId", reportId);
        Report report = reportMapper.selectByPrimaryKey(reportId);
        String jsonResult = JSON.toJSONString(result, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero);
        OrgInfo orgInfo = orgInfoService.getByOrgId(report.getOrgId());
        String orgInfoNumber = orgInfo.getNumber();
        Resource resource = newReportStatisticsService.selectResource();
        String targetPath = reportDetailPath + File.separator + orgInfoNumber + File.separator + reportId + File.separator;
        String jsonPath = targetPath + "data.json";
        String filePath = FileUtil.writeFile(new File(jsonPath), jsonResult);
        String indexPath = targetPath + "index.html";
        File targetIndex = new File(targetPath);
        if (!targetIndex.exists()) {
            targetIndex.delete();
        }
        int bufferSize = 512;
        int len;
        try (InputStream in = new BufferedInputStream(resource.getInputStream(), bufferSize);
             OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(indexPath)), bufferSize)) {
            byte[] buffer = new byte[bufferSize];
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("生成报告json文件出错，id = 【" + reportId + "】");
        }
        return filePath;
    }


    /**
     * 根据批次ID查询报告
     * @author Pan Jianneng
     * @date 2018/12/6 2:39 PM
     * @params [batchId, templateId]
     * @return java.lang.Long
     */
    public Report findByBatchId(Long batchId){
        return reportMapper.findByBatchId(batchId);
    }

    /**
     * 修改状态
     * @author Pan Jianneng
     * @date 2018/12/6 8:08 PM
     * @params [status, reportId]
     * @return void
     */
    public void updateGenerateAgainById(boolean status,Long reportId){
        reportMapper.updateGenerateAgainById(true,reportId);
    }
}
