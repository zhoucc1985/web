package com.cloud.mapper.master.datacollection;

import com.cloud.entity.datacollection.ReportTemplatePageField;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* 报告模板页面字段
* @author Pan jianneng
* @date 2018/11/14
*/
public interface ReportTemplatePageFieldMapper {
    /**
    * 根据ID删除对象
    * @author Pan jianneng
    * @date 2018/11/14
    * @param id
    * @return int
    */
    int deleteByPrimaryKey(Long id);

    /**
    * 单个对象添加值返回成功与否
    * @author Pan jianneng
    * @date 2018/11/14
    * @param record
    * @return int
    */
    int insert(ReportTemplatePageField record);

    /**
    * 单个对象添加返回ID
    * @author Pan jianneng
    * @date 2018/11/14
    * @param record
    * @return int
    */
    int insertSelective(ReportTemplatePageField record);

    /**
    * 根据ID查询单个对象信息
    * @author Pan jianneng
    * @date 2018/11/14
    * @param id
    * @return com.cloud.entity.datacollection.ReportTemplatePageField
    */
    ReportTemplatePageField selectByPrimaryKey(Long id);

    /**
    * 根据ID进行对象修改，只修改有值属性
    * @author Pan jianneng
    * @date 2018/11/14
    * @param record
    * @return int
    */
    int updateByPrimaryKeySelective(ReportTemplatePageField record);

    /**
    * 根据ID修改对象，这个会直接修改为传过来的对象
    * @author Pan jianneng
    * @date 2018/11/14
    * @param record
    * @return int
    */
    int updateByPrimaryKey(ReportTemplatePageField record);

    /**
     * 根据报告模板ID查询包裹所有要导入的字段数据
     * @author Pan Jianneng
     * @date 2018/11/15 12:12 AM
     * @param reportTemplateId 报告模板ID
     * @return
    */
    @Select("select * from report_template_page_field where rt_id=#{reportTemplateId}")
    List<ReportTemplatePageField> queryReportTemplatePageFieldsByReportTemplateId(@Param(value = "reportTemplateId") Long reportTemplateId);

    /**
     * 获取报告模板所有字段数据
     * @author Pan Jianneng
     * @date 2018/11/24 1:18 PM
     * @param reportTemplateId 报告模板ID
     * @return
    */
    List<ReportTemplatePageField> findByReportTemplateId(@Param(value = "reportTemplateId") Long reportTemplateId);
}