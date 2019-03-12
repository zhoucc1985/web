package com.cloud.service.report.impl;

import com.cloud.common.utils.ObjectUtils;
import com.cloud.common.vo.report.ReportVo;
import com.cloud.service.report.ReportService;
import com.cloud.service.report.ReportVcService;
import com.cloud.service.report.ReportVisitRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 报告访问次数统计（基于内存）
 *
 * @author huangYl
 * @date 2018/11/29 10:37
 **/
@Component
public class ReportVcMemoryServiceImpl implements ReportVcService {

    private static final Logger logger = LoggerFactory.getLogger(ReportVcMemoryServiceImpl.class);

    //@Autowired
    //private ReportService reportService;

    @Autowired
    private ReportVisitRecordService visitRecordService;

    @Autowired
    private ServletContext ctx;

    /**
     * 统计报告详情访问次数
     *
     * @param reportId 报告id
     * @param clientIp 客户端ip
     * @author huangYl
     * @date 2018/11/29 11:05
     */
    @Override
    public void selectCountReportVisits(Long reportId, String clientIp) {
        Map<Long, List<String>> ipMap = (Map<Long, List<String>>) ctx.getAttribute("ipMap");
        Map<Long, Integer> ipMapCount = (Map<Long, Integer>) ctx.getAttribute("ipMapCount");
        // 从ctx中获取ipMap
        if (ObjectUtils.isNotEmptyMap(ipMap) && ipMap.containsKey(reportId)) {
            // 在ctx中存在，直接操作内存中的map
            List<String> reportIpList = ipMap.get(reportId);
            if (!reportIpList.contains(clientIp)) {
                synchronized (ipMap) {
                    reportIpList.add(clientIp);
                    Integer count = ipMapCount.get(reportId);
                    ipMapCount.replace(reportId, count + 1);
                    //reportService.updateReportIpRecord(reportId, clientIp);
                }
            }
        } else {
            // 在内存中不存在，查数据库
            Set<String> records = visitRecordService.selectIpListByReportId(reportId);
            if (!records.contains(clientIp)) {
                synchronized (ipMap) {
                    ArrayList<String> ipList = new ArrayList<>();
                    ipList.add(clientIp);
                    ipMap.put(reportId, ipList);
                    ipMapCount.put(reportId, 1);
                    //reportService.updateReportIpRecord(reportId, clientIp);
                }
            }
        }
    }


    /**
     * 用于定时器从数据库读取数据更新内存中的数据，和更新报告中的访问次数
     *
     * @author huangYl
     * @date 2018/11/29 9:47
     */
    @Override
    public void updateCacheDataAndReportVisitCount() {
        // 更新内存中的数据
        updateServletContextData(ctx);
        // 更新报告的访问次数
        Map<Long, Integer> ipMapCount = (Map<Long, Integer>) ctx.getAttribute("ipMapCount");
        if (ObjectUtils.isNotEmptyMap(ipMapCount)) {
            ipMapCount.forEach((k, v) -> {
                //reportService.updateVisitCountById(k, v);
            });
        }
    }

    /**
     * 查询报告列表时，校验内存中是否存在报告的访问次数，如果有设置报告访问次数
     *
     * @param list 报告列表
     * @return void
     * @author huangYl
     * @date 2018/11/29 11:28
     */
    @Override
    public void verifyReportListByMemory(List<ReportVo> list) {
        Map<Long, Integer> ipMapCount = (Map<Long, Integer>) ctx.getAttribute("ipMapCount");
        if (ObjectUtils.isNotEmptyMap(ipMapCount)) {
            for (ReportVo reportVo : list) {
                if (ipMapCount.containsKey(reportVo.getId())) {
                    reportVo.setVisitCount(ipMapCount.get(reportVo.getId()));
                }
            }
        }
    }

    /**
    * 根据报告ID删除内存中的浏览记录
    * @param  reportId 报告id
    * @return void
    * @author huangYl
    * @date   2018/11/30 10:52
    */
    @Override
    public void deleteByReportId(Long reportId) {
        Map<Long, List<String>> ipMap = (Map<Long, List<String>>) ctx.getAttribute("ipMap");
        Map<Long, Integer> ipMapCount = (Map<Long, Integer>) ctx.getAttribute("ipMapCount");
        if (ipMap.containsKey(reportId)) {
            ipMap.remove(reportId);
        }
        if (ipMapCount.containsKey(reportId)) {
            ipMapCount.remove(reportId);
        }
    }


    /**
     * 从数据库读取数据更新ServletContext内存中的数据
     *
     * @param ctx ServletContext
     * @return void
     * @author huangYl
     * @date 2018/11/29 10:01
     */
    private void updateServletContextData(ServletContext ctx) {
        Map<Long, List<String>> ipMap = visitRecordService.selectIpListGroupByReportId();
        Map<Long, Integer> ipMapCount = new ConcurrentHashMap<>(20);
        ipMap.forEach((k, v) -> ipMapCount.put(k, v.size()));
        ctx.setAttribute("ipMap", ipMap);
        ctx.setAttribute("ipMapCount", ipMapCount);
        logger.info("更新了ServletContext中的数据，时间【" + LocalDateTime.now().toString() + "】");
    }
}
