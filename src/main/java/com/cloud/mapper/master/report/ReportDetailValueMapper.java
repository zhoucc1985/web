package com.cloud.mapper.master.report;

import com.cloud.entity.report.ReportDetailValue;

/**
 * 报告详情属性值 Mapper
 *
 * @author huangYl
 * @time 2018/11/15
 */
public interface ReportDetailValueMapper {
    /**
     * 根据id删除
     *
     * @param id id
     * @return int
     * @author huangYl
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增
     *
     * @param record 实体
     * @return int
     * @author huangYl
     */
    int insert(ReportDetailValue record);

    /**
     * 动态新增
     *
     * @param record 实体
     * @return int
     * @author huangYl
     */
    int insertSelective(ReportDetailValue record);

    /**
     * 根据id查询
     *
     * @param id id
     * @return com.cloud.entity.report.ReportDetailValue
     * @author huangYl
     */
    ReportDetailValue selectByPrimaryKey(Long id);

    /**
     * 动态更新
     *
     * @param record 实体
     * @return int
     * @author huangYl
     */
    int updateByPrimaryKeySelective(ReportDetailValue record);

    /**
     * 更新value值
     *
     * @param record 实体
     * @return int
     * @author huangYl
     */
    int updateByPrimaryKeyWithBLOBs(ReportDetailValue record);

    /**
     * 更新id
     *
     * @param record 实体
     * @return int
     * @author huangYl
     */
    int updateByPrimaryKey(ReportDetailValue record);

    /**
     * 根据报告ID删除所有对应的属性值
     *
     * @param reportId 报告id
     * @return void
     * @author huangYl
     * @date 2018/11/22 20:09
     */
    void deleteByReportId(Long reportId);
}