package com.cloud.service.report;

import com.cloud.common.utils.ObjectUtils;
import com.cloud.mapper.master.report.NewReportStatisticsMapper;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 迎新报告统计 service
 *
 * @author huangYl
 * @date 2018/11/19 15:18
 **/

@Service
public class NewReportStatisticsService {

    private static final Logger logger = LoggerFactory.getLogger(NewReportStatisticsService.class);

    @Autowired
    private NewReportStatisticsMapper statisticsMapper;

    /**
     * 根据批次ID统计学校总人数
     *
     * @param batchId 批次id
     * @return java.lang.Integer
     * @author huangYl
     * @date 2018/11/19 15:23
     */
    public Integer selectSchoolHeadcount(Long batchId) {
        Integer count = statisticsMapper.selectSchoolHeadcount(batchId);
        if (null == count) {
            count = 0;
        }
        return count;
    }

    /**
     * 根据批次ID各学院学生人数统计
     *
     * @param batchId 批次id
     * @return java.util.Map
     * @author huangYl
     * @date 2018/11/19 18:27
     */
    public List<Map<String, Long>> selectEveryCollegeCount(Long batchId) {
        List<Map<String, Long>> list = statisticsMapper.selectEveryCollegeCount(batchId);
        if (ObjectUtils.isEmptyList(list)) {
            return Lists.newArrayList();
        }
        return list;
    }

    /**
     * 根据批次ID查询生源省份总个数
     *
     * @param batchId 批次id
     * @return java.lang.Integer
     * @author huangYl
     * @date 2018/11/20 10:41
     */
    public Integer selectCollegeCount(Long batchId) {
        Integer count = statisticsMapper.selectCollegeCount(batchId);
        if (ObjectUtils.isEmptyObject(count)) {
            count = 0;
        }
        return count;
    }

    /**
     * 根据批次ID查询各生源省份学生总人数
     *
     * @param batchId 批次id
     * @return java.util.List
     * @author huangYl
     * @date 2018/11/20 11:09
     */
    public List<Map<String, Long>> selectEveryProvinceCount(Long batchId) {
        List<Map<String, Long>> list = statisticsMapper.selectEveryProvinceCount(batchId);
        if (ObjectUtils.isEmptyList(list)) {
            return Lists.newArrayList();
        }
        return list;
    }

    /**
     * 根据批次ID查询新生生源省会与学校省会距离总和
     *
     * @param provinceCityName 学校省会名称
     * @param batchId          批次id
     * @return java.lang.Integer
     * @author huangYl
     * @date 2018/11/20 14:28
     */
    public Integer selectDistanceTotalSum(String provinceCityName, Long batchId) {
        Integer count = statisticsMapper.selectDistanceTotalSum(provinceCityName, batchId);
        if (ObjectUtils.isEmptyObject(count)) {
            count = 0;
        }
        return count;
    }

    /**
     * 根据批次ID查询全校男女比例
     *
     * @param batchId 批次id
     * @return java.util.List
     * @author huangYl
     * @date 2018/11/21 18:02
     */
    public List<Map<String, String>> selectSexRatio(Long batchId) {
        List<Map<String, String>> list = statisticsMapper.selectSexRatio(batchId);
        // 这里为了兼容只有一个性别时，前端展示
        if (list.size() == 1) {
            String man = "男";
            String girl = "女";
            String sex = "sex";
            Map<String, String> addMap = new HashMap<>(1);
            Map<String, String> map = list.get(0);
            if (man.equals(map.get(sex))) {
                addMap.put("sex", "女");
                addMap.put("percentage", "0.0%");
                addMap.put("count", "0");
                list.add(addMap);
            } else if (girl.equals(map.get(sex))) {
                addMap.put("sex", "男");
                addMap.put("percentage", "0.0%");
                addMap.put("count", "0");
                list.add(0, addMap);
            }

        }
        if (ObjectUtils.isEmptyList(list)) {
            return Lists.newArrayList();
        }
        return list;
    }

    /**
     * 根据批次ID查询男生最多专业Top3及男女人数
     *
     * @param batchId 批次id
     * @return java.util.List
     * @author huangYl
     * @date 2018/11/22 11:08
     */
    public List<Map<String, String>> selectMajorBoyTop3(Long batchId) {
        List<Map<String, String>> list = statisticsMapper.selectMajorBoyTop3(batchId);
        if (ObjectUtils.isEmptyList(list)) {
            return Lists.newArrayList();
        }
        return list;
    }

    /**
     * 根据批次ID查询女生最多专业Top3及男女人数
     *
     * @param batchId 批次id
     * @return java.util.List
     * @author huangYl
     * @date 2018/11/22 11:38
     */
    public List<Map<String, String>> selectMajorGirlTop3(Long batchId) {
        List<Map<String, String>> list = statisticsMapper.selectMajorGirlTop3(batchId);
        if (ObjectUtils.isEmptyList(list)) {
            return Lists.newArrayList();
        }
        return list;
    }

    /**
     * 根据批次ID查询新生录取平均成绩专业排名
     *
     * @param batchId 批次id
     * @return java.util.List
     * @author huangYl
     * @date 2018/11/22 14:23
     */
    public List<Map<String, String>> selectMajorAvgScoreRank(Long batchId) {
        List<Map<String, String>> list = statisticsMapper.selectMajorAvgScoreRank(batchId);
        if (ObjectUtils.isEmptyList(list)) {
            return Lists.newArrayList();
        }
        return list;
    }

    /**
     * 根据批次ID查询同名同姓人数统计最多TOP3姓名
     *
     * @param batchId 批次id
     * @return java.util.List
     * @author huangYl
     * @date 2018/11/22 15:00
     */
    public List<Map<String, String>> selectSameNameTop3(Long batchId) {
        List<Map<String, String>> list = statisticsMapper.selectSameNameTop3(batchId);
        if (ObjectUtils.isEmptyList(list)) {
            return Lists.newArrayList();
        }
        return list;
    }

    /**
     * 根据批次ID查询出生年份人数占比饼图
     *
     * @param batchId 批次id
     * @return java.util.List
     * @author huangYl
     * @date 2018/11/22 16:05
     */
    public List<Map<String, String>> selectCountForYear(Long batchId) {
        List<Map<String, String>> list = statisticsMapper.selectCountForYear(batchId);
        if (ObjectUtils.isEmptyList(list)) {
            return Lists.newArrayList();
        }
        return list;
    }

    /**
     * 根据批次ID查询全校本批新生12星座人数
     *
     * @param batchId 批次id
     * @return java.util.List
     * @author huangYl
     * @date 2018/11/22 16:42
     */
    public List<Map<String, String>> selectCountForConstellation(Long batchId) {
        List<Map<String, String>> list = statisticsMapper.selectCountForConstellation(batchId);
        if (ObjectUtils.isEmptyList(list)) {
            return Lists.newArrayList();
        }
        return list;
    }

    /**
     * 根据批次ID查询同月同日的人数最多TOP3生日
     *
     * @param batchId 批次id
     * @return java.util.List
     * @author huangYl
     * @date 2018/11/22 18:07
     */
    public List<Map<String, String>> selectSameBirthTop3(Long batchId) {
        List<Map<String, String>> list = statisticsMapper.selectSameBirthTop3(batchId);
        if (ObjectUtils.isEmptyList(list)) {
            return Lists.newArrayList();
        }
        return list;
    }

    /**
     * 获取项目资源中的迎新报告的资源index.html
     *
     * @return org.springframework.core.io.Resource
     * @author huangYl
     * @date 2018/12/3 15:42
     */
    public Resource selectResource() {
        return new ClassPathResource("/static/newReport/index.html");
    }
}
