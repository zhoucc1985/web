package com.cloud.mapper.real.datacollect;

import org.apache.ibatis.annotations.Param;

/**
 * @Author: chengt@sunmnet.com
 * @Date: created on 14:18 2019/2/21
 * @Description:
 */

/**
 * 与真实表相关的数据操作
 */
public interface RealTableMapper {
    /**
     * 删除真实表中当前批次导入的数据
     * @param batchId
     * @param tableName
     */
    void deleteRealTableDataByBatchId(@Param("batchId") String batchId, @Param("tableName") String tableName);

}
