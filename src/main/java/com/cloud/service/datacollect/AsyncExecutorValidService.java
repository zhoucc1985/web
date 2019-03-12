package com.cloud.service.datacollect;

import java.util.List;
import java.util.Map;

/**
 * 数据验证服务类
 */
public interface  AsyncExecutorValidService {

    /**
     * 验证单个数据入口
     * @param tmpTableName 临时表名
     * @param id 字段ID
     * @param batchId 批次ID
     * @return
     */
    boolean  validOne(String tmpTableName,Long id,String batchId);

    /**
     * 批量验证数据入口
     * @return
     */
    void  ValidDatas(String tableName,Long templateId,String batchId);
    /**
     *  获取真实表列的属性参数
     * @Param tableName 表名称
     */
    List<Map<String,Object>> getColumnFiledsByTableName(List<String> realTableName);

    /**
     * 获取临时表待验证数据
     * @return
     */
    List<Map<String,Object>> getValidDatas(String tempTableName,String batchId);
    /**
     * 获取验证的顺序列名称包含_id
     */
    List<String> getCloumns(String tableName);

    /**
     * 更新批次记录验证结果
     * @param batchId
     */
    void updateBatchErrorCount(String batchId);

    void updateBatchValidResult(String batchId);
}
