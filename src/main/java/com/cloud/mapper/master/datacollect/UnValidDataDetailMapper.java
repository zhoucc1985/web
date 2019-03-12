package com.cloud.mapper.master.datacollect;

import com.cloud.entity.datacollect.Basic_class_info;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author: chengt@sunmnet.com
 * @Date: created on 17:25 2019/1/14
 * @Description:
 */
public interface UnValidDataDetailMapper {
    /**
     * 无效数据的统计
     * @param batchId 导入批次
     * @return
     */
    Map<String,Object> findInvalidDataStat(@Param("batchId") String batchId);

    /**
     * 通过批次Id，查找临时表名
     * @param batchId 批次ID
     * @return
     */
    String findTmpTableNameByBatchId(@Param("batchId") String batchId);

    /**
     * 根据模板ID查找临时表名
     *
     */
    String findTmpTableNameByTempId(@Param("templateId") Integer templateId);

    /**
     * 通过批次Id和临时表名，查找临时表的字段名
     * @param batchId 批次ID
     * @param tableName 临时表名
     * @return
     */
    List<Map<String,Object>> findTmpTableColumns(@Param("batchId") String batchId,@Param("tableName") String tableName);

    List<Map<String,Object>> findTmpTableColumnsIncludeId(@Param("tableName") String tableName);

    Long selectByPrimaryKey(Long batchId);
    /**
     * 临时表表中文名
     * @param batchId 批次id
     * @return
     */
    String findTmpTableChName(@Param("batchId") String batchId);

    /**
     * 通过批次Id和临时表名，查找临时表的字段名
     * @param batchId 批次ID
     * @param tableName 临时表名
     * @return
     */
    List<Map<String,Object>> findColumnsTbaleList(@Param("batchId") String batchId,@Param("tableName") String tableName);

    /**
     * 查询临时表中所有的字段名
     * @param batchId
     * @param tableName
     * @return
     */
    List<Map<String,Object>> findTableAllColumns(@Param("batchId") String batchId,@Param("tableName") String tableName);

}
