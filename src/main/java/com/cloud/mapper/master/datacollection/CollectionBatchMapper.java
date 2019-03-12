package com.cloud.mapper.master.datacollection;

import com.cloud.entity.datacollection.CollectionBatch;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 *
* 导入数据批次
* @author Pan jianneng
* @date 2018/11/14
*/
public interface CollectionBatchMapper {

	/**
     * 根据采集数据时间 和 模板名称 查询符合条件的 采集数据
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param templateName  模板名称
     * @param orgCode       进行数据过滤的条件
     * @return   采集数据列表
     */
    List<CollectionBatch> searchCollectBatch(@Param(value="startTime")Date startTime,
                                             @Param(value="endTime")Date endTime,
                                             @Param(value="templateName") String templateName,
                                             @Param(value="orgCode") String orgCode);

	/**
     * 根据当前模板 当前时间 查询学校 采集记录
     * @param orgId     学校组织ID
     * @param nowYear   当前年
     * @param templeId  选择模板
     * @return
     */
    List<CollectionBatch> selectByYearRptOrg(@Param(value="orgId")Long orgId, @Param(value = "nowYear") int nowYear,@Param(value = "templeId") Long templeId);
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
    int insert(CollectionBatch record);

    /**
    * 单个对象添加返回ID
    * @author Pan jianneng
    * @date 2018/11/14
    * @param record
    * @return int
    */
    int insertSelective(CollectionBatch record);

    /**
    * 根据ID查询单个对象信息
    * @author Pan jianneng
    * @date 2018/11/14
    * @param id
    * @return com.cloud.entity.datacollection.CollectionBatch
    */
    CollectionBatch selectByPrimaryKey(Long id);

    /**
    * 根据ID进行对象修改，只修改有值属性
    * @author Pan jianneng
    * @date 2018/11/14
    * @param record
    * @return int
    */
    int updateByPrimaryKeySelective(CollectionBatch record);

    /**
    * 根据ID修改对象，这个会直接修改为传过来的对象
    * @author Pan jianneng
    * @date 2018/11/14
    * @param record
    * @return int
    */
    int updateByPrimaryKey(CollectionBatch record);


    /**
     * 根据ID和状态修改状态
     * @author Pan Jianneng
     * @date 2018/11/17 6:10 PM
     * @param id 批次ID
     * @param status 要修改成的状态
     * @return
    */
    @Update("update collection_batch set status = #{status} where id =#{id}")
    void modifyStatusByIdAndStatus(@Param(value="id")Long id,@Param(value = "status") String status);


}