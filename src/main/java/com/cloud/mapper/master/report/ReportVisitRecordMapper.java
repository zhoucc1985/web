package com.cloud.mapper.master.report;

import com.cloud.entity.report.ReportVisitRecord;

import java.util.List;
import java.util.Set;

/**
 * 报告详情访问记录 mapper
 *
 * @author huangYl
 * @time 2018/11/27
 */
public interface ReportVisitRecordMapper {
    /**
     * 新增
     *
     * @param record 实体
     * @return int
     * @author huangYl
     */
    int insert(ReportVisitRecord record);

    /**
     * 根据ID查询
     *
     * @param id
     * @return com.cloud.entity.report.ReportVisitRecord
     * @author huangYl
     */
    ReportVisitRecord selectByPrimaryKey(Long id);

    /**
     * 根据报告ID查询访问次数
     *
     * @param reportId 报告id
     * @return java.lang.Integer 报告访问次数
     * @author huangYl
     * @date 2018/11/27 11:36
     */
    Integer selectCountByReportId(Long reportId);

    /**
     * 根据报告ID查询全部IP地址列表
     *
     * @param reportId 报告id
     * @return java.util.Set
     * @author huangYl
     * @date 2018/11/27 11:48
     */
    Set<String> selectIpListByReportId(Long reportId);

    /**
    * 查询全部
    * @return java.util.List<com.cloud.entity.report.ReportVisitRecord>
    * @author huangYl
    * @date   2018/11/27 15:16
    */
    List<ReportVisitRecord> selectAll();

    /**
     * 根据报告ID删除浏览记录
     *
     * @param reportId 报告id
     * @return void
     * @author huangYl
     * @date 2018/11/30 10:45
     */
    void deleteByReportId(Long reportId);
}