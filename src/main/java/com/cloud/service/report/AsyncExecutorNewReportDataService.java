package com.cloud.service.report;

import com.alibaba.fastjson.JSON;
import com.cloud.common.dict.CommonDict;
import com.cloud.common.excption.BusinessException;
import com.cloud.common.utils.CommonUtils;
import com.cloud.common.utils.DateUtils;
import com.cloud.common.utils.ObjectUtils;
import com.cloud.common.utils.WebSocketSentMsgManager;
import com.cloud.common.vo.report.GenerateReportMsgVo;
import com.cloud.common.vo.report.NewReportConstants;
import com.cloud.entity.datacollection.CollectionBatch;
import com.cloud.entity.datacollection.ReportTemplate;
import com.cloud.entity.report.Report;
import com.cloud.entity.sys.OrgInfo;
import com.cloud.entity.sys.Province;
import com.cloud.entity.sys.ProvinceCity;
import com.cloud.mapper.master.datacollection.CollectionBatchMapper;
import com.cloud.mapper.master.datacollection.CollectionDataStandardMapper;
import com.cloud.mapper.master.datacollection.ReportTemplateMapper;
import com.cloud.mapper.master.report.ReportMapper;
import com.cloud.service.sys.OrgInfoService;
import com.cloud.service.sys.ProvinceCityService;
import com.cloud.service.sys.ProvinceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 异步生成迎新报告
 *
 * @author huangYl
 * @date 2018/11/19 10:57
 **/
@Service
public class AsyncExecutorNewReportDataService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncExecutorNewReportDataService.class);
    /**
     * 绕地球一圈（单位：公里）
     */
    private static final int CIRCLE_THE_EARTH = 40076;
    /**
     * 长征距离（单位：公里）
     */
    private static final int LONG_MARCH_DISTANCE = 12500;

    @Autowired
    private WebSocketSentMsgManager sentMassage;

    @Autowired
    private OrgInfoService orgInfoService;

    @Autowired
    private ReportDetailAttributeService attributeService;

    @Autowired
    private ReportDetailValueService valueService;

    @Autowired
    private ReportTemplateMapper reportTemplateMapper;

    @Autowired
    private CollectionBatchMapper collectionBatchMapper;

    @Autowired
    private NewReportStatisticsService statisticsService;

    @Autowired
    private ProvinceCityService provinceCityService;

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private CollectionDataStandardMapper collectionDataStandardMapper;

    @Autowired
    private ReportDetailValueService reportDetailValueService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private ReportVisitRecordService reportVisitRecordService;

    @Autowired
    private ReportVcFactory reportVcFactory;

    /**
     * 异步统计报告
     *
     * @param report 报告
     * @author huangYl
     * @date 2018/11/19 11:08
     */
    @Async
    public void asyncStatisticReportData(Report report) {
        logger.info(String.format("报告 id = %s 开始生成", report.getId()));
        selectAndStatisticsData(report);
    }

    /**
     * 生成统计报告
     *
     * @param report 报告
     * @return void
     * @author huangYl
     * @date 2018/11/24 17:01
     */
    @Transactional(rollbackFor = Exception.class)
    public void selectAndStatisticsData(Report report) {
        try {
            // 判断是否是再次生成报告的
            if (report.getGenerateAgain() == 1 && Report.REPORT_STATUS_SUCCESS.equals(report.getGenerateStatus())) {
                // 再次生成需要把原本统计的数据全部擦除
                reportDetailValueService.deleteByReportId(report.getId());
                report.setGenerateStatus(Report.REPORT_STATUS_NOMINATED);
                // 删除所有浏览记录
                reportVcFactory.getInstance().deleteByReportId(report.getId());
                reportVisitRecordService.deleteByReportId(report.getId());
            }
            CollectionBatch collectionBatch = collectionBatchMapper.selectByPrimaryKey(report.getBatchId());
            // 查询学校所在省份的省会名称
            String provinceCityName = selectSchoolProvinceCityName(collectionBatch);
            // 查询该模板需求返回的所有字段
            Map<String, Long> fieldMap = attributeService.getAllFieldByRtId(report.getRtId());
            // 获取组织机构学校资料
            OrgInfo orginfo = orgInfoService.getByOrgId(report.getOrgId());
            // 学校名称
            String schoolName = orginfo.getName();
            // 学校logo
            String schoolLogo = orginfo.getSchoolLogoPath();
            // 学校简称
            String schoolNikeName = orginfo.getSchoolNikeName();
            // 模板背景音乐
            ReportTemplate reportTemplate = reportTemplateMapper.selectByPrimaryKey(report.getRtId());
            String templateBgmPath = reportTemplate.getBgmPath();
            // 报告名称
            String reportName = collectionBatch.getReportName();
            // 学校总人数
            Integer students = statisticsService.selectSchoolHeadcount(collectionBatch.getId());
            // 各学院学生人数统计
            List<Map<String, Long>> collegeStatistics = statisticsService.selectEveryCollegeCount(collectionBatch.getId());
            // 生源省份总个数
            Integer fromProvinceSum = statisticsService.selectCollegeCount(collectionBatch.getId());
            // 各生源省份学生总人数
            List<Map<String, Long>> sourceDistribution = statisticsService.selectEveryProvinceCount(collectionBatch.getId());
            // 新生生源省会与学校省会距离总和
            Integer distanceTotalSum = statisticsService.selectDistanceTotalSum(provinceCityName, collectionBatch.getId());
            // 距离总和绕地球总圈数
            Integer earthCircleNum = distanceTotalSum / CIRCLE_THE_EARTH;
            // 距离总和跨长征距离总次数
            Integer longMarchNum = distanceTotalSum / LONG_MARCH_DISTANCE;
            // 全校男女比例
            List<Map<String, String>> sexRatio = statisticsService.selectSexRatio(collectionBatch.getId());
            // 统计男女比例百分比
            for (Map<String, String> map : sexRatio) {
                String countStr = String.valueOf(map.get("count"));
                Double result = Double.valueOf(countStr) / students;
                String percentage = String.format("%.1f", Double.valueOf(result) * 100d) + "%";
                map.put("percentage", percentage);
            }
            // 男生最多专业Top3及男女人数
            List<Map<String, String>> majorBoyDistribution = statisticsService.selectMajorBoyTop3(collectionBatch.getId());
            // 女生最多专业Top3及男女人数
            List<Map<String, String>> majorGirlDistribution = statisticsService.selectMajorGirlTop3(collectionBatch.getId());
            // 新生录取平均成绩专业排名
            List<Map<String, String>> majorScoreDistribution = statisticsService.selectMajorAvgScoreRank(collectionBatch.getId());
            // 出生年份人数占比饼图
            List<Map<String, String>> yearDistribution = statisticsService.selectCountForYear(collectionBatch.getId());
            // 全校本批新生12星座人数
            List<Map<String, String>> starDistribution = statisticsService.selectCountForConstellation(collectionBatch.getId());
            // 配对比例最高的TOP3星座配对
            List<Map<String, String>> starMate = constellationMatchTop3(starDistribution);
            // 同月同日的人数最多TOP3生日
            List<Map<String, String>> sameBirthTop3 = statisticsService.selectSameBirthTop3(collectionBatch.getId());
            // 同名同姓人数统计最多TOP3姓名
            List<Map<String, String>> sameNameTop3 = statisticsService.selectSameNameTop3(collectionBatch.getId());
            // 保存报告详情基础信息
            saveBaseDataToDB(fieldMap, report, schoolName, schoolLogo, schoolNikeName, templateBgmPath, reportName);
            // 保存学校统计相关信息（人数/专业/性别/姓名）
            saveSchoolInformationToDB(fieldMap, report, students, collegeStatistics, sexRatio, majorBoyDistribution, majorGirlDistribution, majorScoreDistribution, sameNameTop3);
            // 保存省份统计数据
            saveProvinceDistanceInformationToDB(fieldMap, report, fromProvinceSum, sourceDistribution, distanceTotalSum, earthCircleNum, longMarchNum);
            // 保存年份以及星座统计数据
            saveBirthDateAndConstellationToDB(fieldMap, report, yearDistribution, starDistribution, starMate, sameBirthTop3);
            // 更新报告状态为已完成，设置生成时间，可否再次生成设为0，设置生成数目，生成报告链接，浏览人数默认为 0，备注，学校logo
            report.setGenerateStatus(Report.REPORT_STATUS_SUCCESS);
            report.setGenerateTime(DateUtils.localDateTimeToDate(LocalDateTime.now()));
            report.setGenerateAgain((byte) 0);
            report.setGenerateNumber(students);
            report.setVisitCount(0);
            report.setSchoolLogoPath(schoolLogo);
            report.setRemark(reportTemplate.getLogoRemark());
            // 生成报告链接
            report.setReportLink("/report/" + report.getId());
            reportMapper.updateByPrimaryKeySelective(report);
            // 已生成的标准数据修改属性为已生成报告
            collectionDataStandardMapper.updateGenerateStatusByBatchId(true, collectionBatch.getId());
            // 生成发消息给前端
            Map<String, Object> sucResultMap = CommonUtils.getSucResultMap(new GenerateReportMsgVo(collectionBatch.getId(),collectionBatch.getBatchNo(), report.getGenerateNumber(), report.getId()));
            sentMassage.callback(sucResultMap);
            logger.info(String.format("报告 id = %s 生成成功", report.getId()));
        } catch (Exception e) {
            logger.error("异常信息:", e);
            Map<String, Object> resMap = CommonUtils.getErrorResultMap(e.getMessage());
            // 发生异常后通知前端
            sentMassage.callback(resMap);
            // 报告状态回滚到未生成状态
            reportMapper.updateStatus(report.getId(), Report.REPORT_STATUS_NOMINATED);
            logger.info(String.format("报告 id = %s 生成失败", report.getId()));
        }
    }


    /**
     * 保存报告详情基础信息
     *
     * @param fieldMap        模板返回字段
     * @param report          报告
     * @param schoolName      学校名称
     * @param schoolLogo      学校logo
     * @param schoolNikeName  学校简称
     * @param templateBgmPath 模板背景音乐
     * @param reportName      报告名称
     * @return void
     * @author huangYl
     * @date 2018/11/24 14:28
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveBaseDataToDB(Map<String, Long> fieldMap, Report report, String schoolName, String schoolLogo, String schoolNikeName, String templateBgmPath, String reportName) {
        if (fieldMap.containsKey(NewReportConstants.SCHOOL_NAME)) {
            valueService.save(report.getId(), fieldMap.get(NewReportConstants.SCHOOL_NAME), JSON.toJSONString(schoolName));
            logger.info("报告详情 id = 【" + report.getId() + "】保存学校名称属性值 value = 【" + schoolName + "】");
        }
        if (fieldMap.containsKey(NewReportConstants.SCHOOL_LOGO)) {
            valueService.save(report.getId(), fieldMap.get(NewReportConstants.SCHOOL_LOGO), JSON.toJSONString(schoolLogo));
            logger.info("报告详情 id = 【" + report.getId() + "】保存学校logo属性值 value= 【" + schoolLogo + "】");
        }
        if (fieldMap.containsKey(NewReportConstants.SCHOOL_NIKE_NAME)) {
            valueService.save(report.getId(), fieldMap.get(NewReportConstants.SCHOOL_NIKE_NAME), JSON.toJSONString(schoolNikeName));
            logger.info("报告详情 id = 【" + report.getId() + "】保存学校简称属性值 value = 【" + schoolNikeName + "】");
        }
        if (fieldMap.containsKey(NewReportConstants.TEMPLATE_BGM_PATH)) {
            valueService.save(report.getId(), fieldMap.get(NewReportConstants.TEMPLATE_BGM_PATH), JSON.toJSONString(templateBgmPath));
            logger.info("报告详情 id = 【" + report.getId() + "】保存模板背景音乐属性值 value = 【" + templateBgmPath + "】");
        }
        if (fieldMap.containsKey(NewReportConstants.REPORT_NAME)) {
            valueService.save(report.getId(), fieldMap.get(NewReportConstants.REPORT_NAME), JSON.toJSONString(reportName));
            logger.info("报告详情 id = 【" + report.getId() + "】保存报告名称属性值 value = 【" + reportName + "】");
        }
    }

    /**
     * 保存学校统计相关信息（人数/专业/性别/姓名）
     *
     * @param fieldMap               模板返回字段
     * @param report                 报告
     * @param students               学校总人数
     * @param collegeStatistics      各学院学生人数统计
     * @param sexRatio               统计男女比例百分比
     * @param majorBoyDistribution   男生最多专业Top3及男女人数
     * @param majorGirlDistribution  女生最多专业Top3及男女人数
     * @param majorScoreDistribution 新生录取平均成绩专业排名
     * @param sameNameTop3           同名同姓人数统计最多TOP3姓名
     * @author huangYl
     * @date 2018/11/24 14:50
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveSchoolInformationToDB(Map<String, Long> fieldMap, Report report, Integer students, List<Map<String, Long>> collegeStatistics, List<Map<String, String>> sexRatio, List<Map<String, String>> majorBoyDistribution, List<Map<String, String>> majorGirlDistribution, List<Map<String, String>> majorScoreDistribution, List<Map<String, String>> sameNameTop3) {
        if (fieldMap.containsKey(NewReportConstants.STUDENTS)) {
            valueService.save(report.getId(), fieldMap.get(NewReportConstants.STUDENTS), JSON.toJSONString(students));
            logger.info("报告详情 id = 【" + report.getId() + "】保存学校总人数属性值 value = 【" + students + "】");
        }
        if (fieldMap.containsKey(NewReportConstants.COLLEGE_DISTRIBUTION)) {
            valueService.save(report.getId(), fieldMap.get(NewReportConstants.COLLEGE_DISTRIBUTION), JSON.toJSONString(collegeStatistics));
            logger.info("报告详情 id = 【" + report.getId() + "】保存各学院学生人数统计属性值 value = 【" + collegeStatistics + "】");
        }
        if (fieldMap.containsKey(NewReportConstants.SEX_RATIO)) {
            valueService.save(report.getId(), fieldMap.get(NewReportConstants.SEX_RATIO), JSON.toJSONString(sexRatio));
            logger.info("报告详情 id = 【" + report.getId() + "】保存全校男女比例属性值 value = 【" + sexRatio + "】");
        }
        if (fieldMap.containsKey(NewReportConstants.MAJOR_BOY_DISTRIBUTION)) {
            valueService.save(report.getId(), fieldMap.get(NewReportConstants.MAJOR_BOY_DISTRIBUTION), JSON.toJSONString(majorBoyDistribution));
            logger.info("报告详情 id = 【" + report.getId() + "】保存男生最多专业Top3及男女人数属性值 value = 【" + majorBoyDistribution + "】");
        }
        if (fieldMap.containsKey(NewReportConstants.MAJOR_GIRL_DISTRIBUTION)) {
            valueService.save(report.getId(), fieldMap.get(NewReportConstants.MAJOR_GIRL_DISTRIBUTION), JSON.toJSONString(majorGirlDistribution));
            logger.info("报告详情 id = 【" + report.getId() + "】保存女生最多专业Top3及男女人数属性值 value = 【" + majorGirlDistribution + "】");
        }
        if (fieldMap.containsKey(NewReportConstants.MAJOR_SCORE_DISTRIBUTION)) {
            valueService.save(report.getId(), fieldMap.get(NewReportConstants.MAJOR_SCORE_DISTRIBUTION), JSON.toJSONString(majorScoreDistribution));
            logger.info("报告详情 id = 【" + report.getId() + "】保存新生录取平均成绩专业排名属性值 value = 【" + majorScoreDistribution + "】");
        }
        if (fieldMap.containsKey(NewReportConstants.SAME_NAMETOP3)) {
            valueService.save(report.getId(), fieldMap.get(NewReportConstants.SAME_NAMETOP3), JSON.toJSONString(sameNameTop3));
            logger.info("报告详情 id = 【" + report.getId() + "】保存同名同姓人数统计最多TOP3姓名属性值 value = 【" + sameNameTop3 + "】");
        }
    }

    /**
     * 保存省份统计数据
     *
     * @param fieldMap           模板返回字段
     * @param report             报告
     * @param fromProvinceSum    生源省份总个数
     * @param sourceDistribution 各生源省份学生总人数统计
     * @param distanceTotalSum   新生生源省会与学校省会距离总和
     * @param earthCircleNum     距离总和绕地球总圈数
     * @param longMarchNum       距离总和跨长征距离总次数
     * @author huangYl
     * @date 2018/11/24 15:05
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveProvinceDistanceInformationToDB(Map<String, Long> fieldMap, Report report, Integer fromProvinceSum, List<Map<String, Long>> sourceDistribution, Integer distanceTotalSum, Integer earthCircleNum, Integer longMarchNum) {
        if (fieldMap.containsKey(NewReportConstants.FROM_PROVINCE_SUM)) {
            valueService.save(report.getId(), fieldMap.get(NewReportConstants.FROM_PROVINCE_SUM), JSON.toJSONString(fromProvinceSum));
            logger.info("报告详情 id = 【" + report.getId() + "】保存生源省份总个数属性值 value = 【" + fromProvinceSum + "】");
        }
        if (fieldMap.containsKey(NewReportConstants.SOURCE_DISTRIBUTION)) {
            valueService.save(report.getId(), fieldMap.get(NewReportConstants.SOURCE_DISTRIBUTION), JSON.toJSONString(sourceDistribution));
            logger.info("报告详情 id = 【" + report.getId() + "】保存各生源省份学生总人数统计属性值 value = 【" + sourceDistribution + "】");
        }
        if (fieldMap.containsKey(NewReportConstants.DISTANCE_TOTAL_SUM)) {
            valueService.save(report.getId(), fieldMap.get(NewReportConstants.DISTANCE_TOTAL_SUM), JSON.toJSONString(distanceTotalSum));
            logger.info("报告详情 id = 【" + report.getId() + "】保存新生生源省会与学校省会距离总和属性值 value = 【" + distanceTotalSum + "】");
        }
        if (fieldMap.containsKey(NewReportConstants.EARTH_CIRCLE_NUM)) {
            valueService.save(report.getId(), fieldMap.get(NewReportConstants.EARTH_CIRCLE_NUM), JSON.toJSONString(earthCircleNum));
            logger.info("报告详情 id = 【" + report.getId() + "】保存距离总和绕地球总圈数属性值 value = 【" + earthCircleNum + "】");
        }
        if (fieldMap.containsKey(NewReportConstants.LONG_MARCH_NUM)) {
            valueService.save(report.getId(), fieldMap.get(NewReportConstants.LONG_MARCH_NUM), JSON.toJSONString(longMarchNum));
            logger.info("报告详情 id = 【" + report.getId() + "】保存距离总和跨长征距离总次数属性值 value = 【" + longMarchNum + "】");
        }
    }

    /**
     * 保存年份以及星座统计数据
     *
     * @param fieldMap         模板返回字段
     * @param report           报告
     * @param yearDistribution 出生年份人数占比
     * @param starDistribution 全校本批新生12星座人数
     * @param starMate         配对比例最高的TOP3星座
     * @param sameBirthTop3    同月同日的人数最多TOP3生日
     * @author huangYl
     * @date 2018/11/24 15:14
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveBirthDateAndConstellationToDB(Map<String, Long> fieldMap, Report report, List<Map<String, String>> yearDistribution, List<Map<String, String>> starDistribution, List<Map<String, String>> starMate, List<Map<String, String>> sameBirthTop3) {
        if (fieldMap.containsKey(NewReportConstants.YEAR_DISTRIBUTION)) {
            valueService.save(report.getId(), fieldMap.get(NewReportConstants.YEAR_DISTRIBUTION), JSON.toJSONString(yearDistribution));
            logger.info("报告详情 id = 【" + report.getId() + "】保存出生年份人数占比饼图属性值 value = 【" + yearDistribution + "】");
        }
        if (fieldMap.containsKey(NewReportConstants.STAR_DISTRIBUTION)) {
            valueService.save(report.getId(), fieldMap.get(NewReportConstants.STAR_DISTRIBUTION), JSON.toJSONString(starDistribution));
            logger.info("报告详情 id = 【" + report.getId() + "】保存全校本批新生12星座人数属性值 value = 【" + starDistribution + "】");
        }
        if (fieldMap.containsKey(NewReportConstants.STAR_MATE)) {
            valueService.save(report.getId(), fieldMap.get(NewReportConstants.STAR_MATE), JSON.toJSONString(starMate));
            logger.info("报告详情 id = 【" + report.getId() + "】保存配对比例最高的TOP3星座配对属性值 value = 【" + starMate + "】");
        }
        if (fieldMap.containsKey(NewReportConstants.SAME_BIRTHTOP3)) {
            valueService.save(report.getId(), fieldMap.get(NewReportConstants.SAME_BIRTHTOP3), JSON.toJSONString(sameBirthTop3));
            logger.info("报告详情 id = 【" + report.getId() + "】保存同月同日的人数最多TOP3生日属性值 value = 【" + sameBirthTop3 + "】");
        }
    }

    /**
     * 查询学校所在的省份的省会
     *
     * @param collectionBatch 批次ID
     * @return java.lang.String
     * @author huangYl
     * @date 2018/11/26 11:46
     */
    private String selectSchoolProvinceCityName(CollectionBatch collectionBatch) {
        OrgInfo orgInfo = orgInfoService.getByOrgId(collectionBatch.getOrgId());
        List<ProvinceCity> provinceCityList = provinceCityService.selectAll();
        String provinceCityName = "";
        // 查询学校所在的省份
        Province provinceInfo = provinceService.getById(orgInfo.getProvinceId());
        if (ObjectUtils.isNotEmptyObject(provinceInfo)) {
            provinceCityName = provinceInfo.getName();
            // 匹配省 获取当前的省会城市
            for (ProvinceCity pc : provinceCityList) {
                String province = pc.getProvince();
                Pattern p = Pattern.compile(province + "|" + province + "省" + "|" + province + "市");
                Matcher m = p.matcher(provinceCityName);
                if (m.find()) {
                    return pc.getProvinceCity();
                }
            }
        }
        // 学校没有设置它所在省份的时候，通过递归的方式从学校名称去匹配，匹配不到则匹配对应上级省厅的名称，查询出学校所在省会
        provinceCityName = recursionSelectProvinceCity(orgInfo, provinceCityList);
        if (ObjectUtils.isNotEmptyString(provinceCityName)) {
            return provinceCityName;
        } else {
            logger.error("没有匹配到任何省会");
            throw new BusinessException("没有匹配到任何省会");
        }
    }

    /**
     * 递归根据机构的名称获取其所在的省会对应的省会
     *
     * @param orgInfo          当前的组织机构
     * @param provinceCityList 省份和省会列表
     * @return java.lang.String 机构所属的省会对象的城市
     * @author huangYl
     * @date 2018/11/20 17:38
     */
    private String recursionSelectProvinceCity(OrgInfo orgInfo, List<ProvinceCity> provinceCityList) {
        logger.info("匹配机构id = 【" + orgInfo.getId() + "】，名称 = 【" + orgInfo.getName() + "】 省会");
        if (CommonDict.ORG_TYPE_EDUCATION.equals(orgInfo.getType())) {
            throw new BusinessException("该机构没有匹配到对应的省份，请再次确认");
        }
        String orgInfoName = orgInfo.getName();
        // 匹配省 获取当前的省会城市
        for (ProvinceCity pc : provinceCityList) {
            String province = pc.getProvince();
            Pattern p = Pattern.compile(province + "|" + province + "省" + "|" + province + "市");
            Matcher m = p.matcher(orgInfoName);
            if (m.find()) {
                return pc.getProvinceCity();
            }
        }
        OrgInfo parent = orgInfoService.getByCode(orgInfo.getParentCode());
        return recursionSelectProvinceCity(parent, provinceCityList);
    }

    /**
     * 12星座配对比例最高的TOP3
     *
     * @param list 12星座对应人数
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @author huangYl
     * @date 2018/11/23 16:15
     */
    private List<Map<String, String>> constellationMatchTop3(List<Map<String, String>> list) {
        List<Map<String, String>> listTop3 = new ArrayList<>();
        Map<Double, String> allMap = new HashMap(144);
        String matched = "";
        String match = "";
        double matchedNum = 0d;
        double matchNum = 0d;
        for (int i = 0; list.size() > i; i++) {
            matched = ((HashMap) list.get(i)).get("constellation").toString();
            matchedNum = Integer.valueOf(((HashMap) list.get(i)).get("count").toString());
            for (int j = 0; list.size() > j; j++) {
                // 获取星座间的匹配率
                double mate = constellationsMate(matched, ((HashMap) list.get(j)).get("constellation").toString());
                match = ((HashMap) list.get(j)).get("constellation").toString();
                matchNum = Integer.valueOf(((HashMap) list.get(j)).get("count").toString());
                // 取两个星座人数较小的作被除数，计算匹配率
                double v = matchNum > matchedNum ? matchedNum / matchNum * mate : matchNum / matchedNum * mate;
                // 保存所有星座的匹配率
                allMap.put(v, matched + ":" + match);
            }
        }
        Map<Double, String> finalMap = new LinkedHashMap<>(3);
        // 排序取匹配率最高的三个
        allMap.entrySet().stream().sorted(Map.Entry.<Double, String>comparingByKey().reversed()).limit(3).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));
        finalMap.forEach((k, v) -> {
            // 组装返回map
            HashMap<String, String> map = new HashMap<>(3);
            map.put("matched", v.split(":")[0]);
            map.put("match", v.split(":")[1]);
            map.put("percentage", String.format("%.1f", k * 100d) + "%");
            listTop3.add(map);
        });
        return listTop3;
    }

    /**
     * 根据被匹配的星座和对象的匹配的星座查询当前的几率
     *
     * @param matched 被匹配的星座
     * @param match   匹配的星座
     * @return double 星座匹配几率
     * @author huangYl
     * @date 2018/11/23 16:15
     */
    private static double constellationsMate(String matched, String match) {
        double ratio = 0.0;
        String[] str = {"摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座"};
        double[][] arr = {
                {0.8, 0.7, 0.9, 0.5, 1.0, 0.4, 0.6, 0.4, 1.0, 0.5, 0.9, 0.7},
                {0.7, 0.8, 0.7, 0.9, 0.5, 1.0, 0.4, 0.6, 0.4, 1.0, 0.5, 0.9},
                {0.9, 0.7, 0.8, 0.7, 0.9, 0.5, 1.0, 0.4, 0.6, 0.4, 1.0, 0.5},
                {0.5, 0.9, 0.7, 0.8, 0.7, 0.9, 0.5, 1.0, 0.4, 0.6, 0.4, 1.0},
                {1.0, 0.5, 0.9, 0.7, 0.8, 0.7, 0.9, 0.5, 1.0, 0.4, 0.6, 0.4},
                {0.4, 1.0, 0.5, 0.9, 0.7, 0.8, 0.7, 0.9, 0.5, 1.0, 0.4, 0.6},
                {0.6, 0.4, 1.0, 0.5, 0.9, 0.7, 0.8, 0.7, 0.9, 0.5, 1.0, 0.4},
                {0.4, 0.6, 0.4, 1.0, 0.5, 0.9, 0.7, 0.8, 0.7, 0.9, 0.5, 1.0},
                {1.0, 0.4, 0.6, 0.4, 1.0, 0.5, 0.9, 0.7, 0.8, 0.7, 0.9, 0.5},
                {0.5, 1.0, 0.4, 0.6, 0.4, 1.0, 0.5, 0.9, 0.7, 0.8, 0.7, 0.9},
                {0.9, 0.5, 1.0, 0.4, 0.6, 0.4, 1.0, 0.5, 0.9, 0.7, 0.8, 0.7},
                {0.7, 0.9, 0.5, 1.0, 0.4, 0.6, 0.4, 1.0, 0.5, 0.9, 0.7, 0.8}
        };
        for (int i = 0; str.length > i; i++) {
            if (str[i].equals(matched)) {
                for (int j = 0; arr[i].length > j; j++) {
                    if (str[j].equals(match)) {
                        ratio = arr[i][j];
                    }
                }
            }
        }
        return ratio;
    }
}
