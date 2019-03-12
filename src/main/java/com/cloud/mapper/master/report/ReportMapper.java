package com.cloud.mapper.master.report;

import com.cloud.common.qo.ReportQo;
import com.cloud.common.vo.report.ReportDetailVo;
import com.cloud.common.vo.report.ReportVo;
import com.cloud.entity.report.Report;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 报告 Mapper
 *
 * @author huangYl
 * @time 2018/11/15
 */
public interface ReportMapper {
    /**
     * 根据id删除
     *
     * @param id 报告id
     * @return int
     * @author huangYl
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增报告
     *
     * @param record 报告实体
     * @return int
     * @author huangYl
     */
    int insert(Report record);

    /**
     * 动态新增报告
     *
     * @param record 报告实体
     * @return int
     * @author huangYl
     */
    int insertSelective(Report record);

    /**
     * 根据id查询
     *
     * @param id 报告id
     * @return com.cloud.entity.report.Report
     * @author huangYl
     */
    Report selectByPrimaryKey(Long id);

    /**
     * 动态更新
     *
     * @param record 报告实体
     * @return int
     * @author huangYl
     */
    int updateByPrimaryKeySelective(Report record);

    /**
     * 更新
     *
     * @param record 报告实体
     * @return int
     * @author huangYl
     */
    int updateByPrimaryKey(Report record);

    /**
     * 根据查询条件获取分页数据
     *
     * @param qo 查询对象
     * @return java.util.List<com.cloud.entity.report.Report>
     * @author huangYl
     */
    List<ReportVo> queryPage(ReportQo qo);

    /**
     * * 根据ID查询报告详情
     *
     * @param reportId 报告id
     * @return java.util.List<com.cloud.common.vo.report.ReportDetailVo>
     * @author huangYl
     * @date 2018/11/19 19:47
     */
    List<ReportDetailVo> getReportDetailById(Long reportId);

    /**
     * 更新报告状态
     *
     * @param id     报告id
     * @param status 报告状态
     * @return void
     * @author huangYl
     * @date 2018/11/22 20:32
     */
    void updateStatus(@Param("id") Long id, @Param("status") String status);

    /**
     * 根据ID更新报告访问次数
     *
     * @param id    报告id
     * @param count 访问次数
     * @author huangYl
     * @date 2018/11/27 16:57
     */
    void updateVisitCountById(@Param("id") Long id,@Param("count") Integer count);

    /**
     * 根据报告ID修改报告是否可以再次生成状态
     *
     * @param againStatus 再次生成状态
     * @param id          报告id
     * @return void
     * @author huangYl
     * @date 2018/11/30 16:11
     */
    void updateGenerateAgainById(@Param("againStatus") boolean againStatus,@Param("id") Long id);

    /**
     * 根据批次号ID查询
     * @author Pan Jianneng
     * @date 2018/12/5 8:34 PM
     * @param batchId 批次ID
     * @return Report
     */
    @Select("select * from report where batch_id=#{batchId}")
    @ResultMap("BaseResultMap")
    Report findByBatchId(@Param(value = "batchId") Long batchId);


}