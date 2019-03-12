package com.cloud.mapper.master.report;

import com.cloud.entity.report.ReportDetailAttribute;

import java.util.List;

/**
 * 报告详情属性 Mapper
 *
 * @author huangYl
 * @time 2018/11/15
 */
public interface ReportDetailAttributeMapper {
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
    int insert(ReportDetailAttribute record);

    /**
     * 动态新增
     *
     * @param record 实体
     * @return int
     * @author huangYl
     */
    int insertSelective(ReportDetailAttribute record);

    /**
     * 根据id查询
     *
     * @param id
     * @return com.cloud.entity.report.ReportDetailAttribute
     * @author huangYl
     * @date 2018/11/15 11:04
     */
    ReportDetailAttribute selectByPrimaryKey(Long id);

    /**
     * 动态更新
     *
     * @param record 实体
     * @return int
     * @author huangYl
     */
    int updateByPrimaryKeySelective(ReportDetailAttribute record);

    /**
     * 更新id
     *
     * @param record 实体
     * @return int
     * @author huangYl
     */
    int updateByPrimaryKey(ReportDetailAttribute record);

    /**
     * 根据模板id获取该模板需要呈现的所有字段
     *
     * @param rtId 模板id
     * @return java.util.Map<java.lang.String,java.lang.Long>
     * @author huangYl
     * @date 2018/11/19 14:02
     */
    List<ReportDetailAttribute> getAllFieldByRtId(Long rtId);
}