package com.cloud.mapper.master.report;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 迎新报告统计 mapper
 *
 * @author huangYl
 * @date 2018/11/19
 **/

public interface NewReportStatisticsMapper {
    /**
     * 根据批次ID统计学校总人数
     *
     * @param batchId 批次id
     * @return java.lang.Integer
     * @author huangYl
     * @date 2018/11/19 15:23
     */
    Integer selectSchoolHeadcount(Long batchId);

    /**
     * 根据批次ID各学院学生人数统计
     *
     * @param batchId 批次id
     * @return java.util.Map<java.lang.String,java.lang.Long>
     * @author huangYl
     * @date 2018/11/19 18:27
     */
    List<Map<String, Long>> selectEveryCollegeCount(Long batchId);

    /**
     * 根据批次ID查询生源省份总个数
     *
     * @param batchId 批次id
     * @return java.lang.Integer
     * @author huangYl
     * @date 2018/11/20 10:42
     */
    Integer selectCollegeCount(Long batchId);

    /**
     * 根据批次ID查询各生源省份学生总人数
     *
     * @param batchId 批次id
     * @return java.util.List<java.util.Map<java.lang.String,ava.lang.Long>>
     * @author huangYl
     * @date 2018/11/20 11:16
     */
    List<Map<String, Long>> selectEveryProvinceCount(Long batchId);

    /**
     * 根据批次ID查询新生生源省会与学校省会距离总和
     *
     * @param provinceCityName 学校省会名称
     * @param batchId          批次id
     * @return java.lang.Integer
     * @author huangYl
     * @date 2018/11/20 14:30
     */
    Integer selectDistanceTotalSum(@Param("provinceCityName") String provinceCityName,@Param("batchId") Long batchId);

    /**
     * 根据批次ID查询全校男女比例
     *
     * @param batchId 批次id
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @author huangYl
     * @date 2018/11/21 18:04
     */
    List<Map<String, String>> selectSexRatio(Long batchId);

    /**
    * 根据批次ID查询男生最多专业Top3及男女人数
    * @param  batchId 批次id
    * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
    * @author huangYl
    * @date   2018/11/22 11:09
    */
    List<Map<String,String>> selectMajorBoyTop3(Long batchId);

    /**
     * 根据批次ID查询女生最多专业Top3及男女人数
     *
     * @param batchId 批次id
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @author huangYl
     * @date 2018/11/22 11:39
     */
    List<Map<String,String>> selectMajorGirlTop3(Long batchId);

    /**
     * 根据批次ID查询新生录取平均成绩专业排名
     *
     * @param batchId 批次id
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @author huangYl
     * @date 2018/11/22 14:24
     */
    List<Map<String,String>> selectMajorAvgScoreRank(Long batchId);

    /**
     * 根据批次ID查询同名同姓人数统计最多TOP3姓名
     *
     * @param batchId 批次id
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @author huangYl
     * @date 2018/11/22 15:01
     */
    List<Map<String, String>> selectSameNameTop3(Long batchId);

    /**
     * 根据批次ID查询出生年份人数占比饼图
     *
     * @param batchId 批次id
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @author huangYl
     * @date 2018/11/22 16:06
     */
    List<Map<String,String>> selectCountForYear(Long batchId);

    /**
     * 根据批次ID查询全校本批新生12星座人数
     *
     * @param batchId 批次id
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @author huangYl
     * @date 2018/11/22 16:47
     */
    List<Map<String,String>> selectCountForConstellation(Long batchId);

    /**
     * 根据批次ID查询同月同日的人数最多TOP3生日
     *
     * @param batchId 批次id
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @author huangYl
     * @date 2018/11/22 18:10
     */
    List<Map<String,String>> selectSameBirthTop3(Long batchId);
}
