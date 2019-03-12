package com.cloud.mapper.master.datacollect;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: chengt@sunmnet.com
 * @Date: created on 10:51 2019/1/20
 * @Description:
 */

public interface ExcelMapper {
    /**
     * 通过模板ID查询出临时表名
     * @param templateId
     * @return
     */
    String findTmpTableName(@Param("templateId") Integer templateId);

    /**
     * 查询临时表所有字段(已经导入库的临时表的原数据对应字段，排序后)
     * 不包含唯一id
     * @param tableName
     * @return
     */
    List<Map<String,Object>> findTmpTableColumns(@Param("tableName") String tableName);



}
