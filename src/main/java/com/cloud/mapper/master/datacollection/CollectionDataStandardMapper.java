package com.cloud.mapper.master.datacollection;

import com.cloud.entity.datacollection.CollectionDataStandard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 采集数据标准数据
* @author Pan jianneng
* @date 2018/11/14
*/
public interface CollectionDataStandardMapper {
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
    int insert(CollectionDataStandard record);

    /**
    * 单个对象添加返回ID
    * @author Pan jianneng
    * @date 2018/11/14
    * @param record
    * @return int
    */
    int insertSelective(CollectionDataStandard record);

    /**
    * 根据ID查询单个对象信息
    * @author Pan jianneng
    * @date 2018/11/14
    * @param id
    * @return com.cloud.entity.datacollection.CollectionDataStandard
    */
    CollectionDataStandard selectByPrimaryKey(Long id);

    /**
    * 根据ID进行对象修改，只修改有值属性
    * @author Pan jianneng
    * @date 2018/11/14
    * @param record
    * @return int
    */
    int updateByPrimaryKeySelective(CollectionDataStandard record);

    /**
    * 根据ID修改对象，这个会直接修改为传过来的对象
    * @author Pan jianneng
    * @date 2018/11/14
    * @param record
    * @return int
    */
    int updateByPrimaryKey(CollectionDataStandard record);

    /**
     * 批量插入，忽略学号与学院一致的数据
     * @author Pan Jianneng
     * @date 2018/11/15 8:13 PM
     * @param list
     * @return
     */
    void insertOrUpdateStandardByBatch(List<CollectionDataStandard> list);

    /**
    * 根据批次ID查询
    * @param  batchId 批次ID
    * @return java.util.List<com.cloud.entity.datacollection.CollectionDataStandard>
    * @author huangYl
    * @date   2018/11/24
    */
    List<CollectionDataStandard> selectAllByBatchId(Long batchId);
    
    /** 
    * 根据批次ID更新数据是否生成报告状态
    * @param  status 状态
    * @param  batchId 批次ID
    * @author huangYl
    * @date   2018/11/24
    */ 
    void updateGenerateStatusByBatchId(@Param("status") boolean status,@Param("batchId") Long batchId);
}