package com.cloud.service.datacollect;

import java.util.List;

import com.cloud.entity.datacollect.CollectColumns;
import com.cloud.entity.datacollect.CollectTemplate;

/**
 * 真实表管理类
 */
public interface RealTableService {
    /**
     * 根据批次删除表数据
     */
    Integer  deleteRealTableDataByBatchId(String batchId,String tableNme);
    /**
     * 当前批次导入真实表
     */
    /**
     * 
     * @param resultStr 列名
     * @param tempTableName 来源表
     * @param realTableName 目标表
     */
    void insertRealTableName(String resultStr,String tempTableName,String realTableName,String batchId);
    /**
     * 根据批次id修改状态
     * @param batchId
     */
    void updateImportDetail(String batchId);
    
    List<CollectTemplate> findAllRealTableName();
    
    List<CollectColumns> findCollectColumns();
}
