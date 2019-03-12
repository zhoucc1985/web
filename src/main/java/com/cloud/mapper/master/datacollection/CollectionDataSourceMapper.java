package com.cloud.mapper.master.datacollection;

import com.cloud.entity.datacollection.CollectionDataSource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* 采集数据原数据
* @author Pan jianneng
* @date 2018/11/14
*/
public interface CollectionDataSourceMapper {
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
    int insert(CollectionDataSource record);

    /**
    * 单个对象添加返回ID
    * @author Pan jianneng
    * @date 2018/11/14
    * @param record
    * @return int
    */
    int insertSelective(CollectionDataSource record);

    /**
    * 根据ID查询单个对象信息
    * @author Pan jianneng
    * @date 2018/11/14
    * @param id
    * @return com.cloud.entity.datacollection.CollectionDataSource
    */
    CollectionDataSource selectByPrimaryKey(Long id);

    /**
    * 根据ID进行对象修改，只修改有值属性
    * @author Pan jianneng
    * @date 2018/11/14
    * @param record
    * @return int
    */
    int updateByPrimaryKeySelective(CollectionDataSource record);

    /**
    * 根据ID修改对象，这个会直接修改为传过来的对象
    * @author Pan jianneng
    * @date 2018/11/14
    * @param record
    * @return int
    */
    int updateByPrimaryKey(CollectionDataSource record);

    /**
     *
     * 批量插入，遇到学号与学院一致的更新，会返回ID
     *
     * @author Pan Jianneng
     * @date 2018/11/15 8:13 PM
     * @param list 采集数据原数据集合
     * @return int
     */
    int insertOrUpdateSourceByBatch(List<CollectionDataSource> list);

    /**
     *
     * 批量插入，遇到学号与学院一致的更新，不会返回ID
     *
     * @author Pan Jianneng
     * @date 2018/11/15 8:13 PM
     * @param list 采集数据原数据集合
     * @return int
     */
    int insertOrUpdateSourceByBatchNoReturnId(List<CollectionDataSource> list);

    /**
     * 批量删除没有错误的原始数据
     * @author Pan Jianneng
     * @date 2018/11/17 5:42 PM
     * @param ids 没有错误的原始数据ID集合
     * @return 
    */
    void batchDelSourceById(@Param(value = "ids") List<Long> ids);

    /**
     * 查询所有原数据
     * @author Pan Jianneng
     * @date 2018/11/17 6:26 PM
     * @param 
     * @return 
    */
    List<CollectionDataSource> findAll();

    /**
     * 获取所有不是错误的数据，表示没有经过校验的数据
     * @author Pan Jianneng
     * @date 2018/11/23 4:39 PM
     * @param
     * @return
    */
    @Select("select * from collection_data_source where is_error=1")
    @ResultMap("BaseResultMap")
    List<CollectionDataSource> findAllNoErrorList();

    /**
     * 根据批次查询有错误的原数据
     * @author Pan Jianneng
     * @date 2018/11/23 12:00 PM
     * @param batchId 批次ID
     * @return
    */
    @Select("select * from collection_data_source where is_error=1 and batch_id=#{batchId}")
    @ResultMap("BaseResultMap")
    List<CollectionDataSource> findErrorsByBatchId(@Param("batchId") Long batchId);

    /**
     * 查询批次没有错误的原数据，用于清除
     * @author Pan Jianneng
     * @date 2018/12/3 4:18 PM
     * @param batchId 批次ID
     * @return
    */
    @Select("select * from collection_data_source where is_error=0 and batch_id=#{batchId}")
    @ResultMap("BaseResultMap")
    List<CollectionDataSource> findNoErrorByBatchId(@Param(value = "batchId") Long batchId);
}