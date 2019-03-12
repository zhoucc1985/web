package com.cloud.mapper.master.datacollection;

import com.cloud.entity.datacollection.ReportTemplatePage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 报告模板页面
* @author Pan jianneng
* @date 2018/11/14
*/
public interface ReportTemplatePageMapper {
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
    int insert(ReportTemplatePage record);

    /**
    * 单个对象添加返回ID
    * @author Pan jianneng
    * @date 2018/11/14
    * @param record
    * @return int
    */
    int insertSelective(ReportTemplatePage record);

    /**
    * 根据ID查询单个对象信息
    * @author Pan jianneng
    * @date 2018/11/14
    * @param id
    * @return com.cloud.entity.datacollection.ReportTemplatePage
    */
    ReportTemplatePage selectByPrimaryKey(Long id);

    /**
    * 根据ID进行对象修改，只修改有值属性
    * @author Pan jianneng
    * @date 2018/11/14
    * @param record
    * @return int
    */
    int updateByPrimaryKeySelective(ReportTemplatePage record);

    /**
    * 根据ID修改对象，这个会直接修改为传过来的对象
    * @author Pan jianneng
    * @date 2018/11/14
    * @param record
    * @return int
    */
    int updateByPrimaryKey(ReportTemplatePage record);

    /**
     * 获取报告模板所有页面
     * @author Pan Jianneng
     * @date 2018/11/24 1:17 PM
     * @param reportTemplateId 报告模板ID
     * @return
    */
    List<ReportTemplatePage> findByReportTemplateId(@Param(value = "reportTemplateId") Long reportTemplateId);
}