package com.cloud.common.utils.cloud;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author: chengt@sunmnet.com
 * @Date: created on 12:12 2019/1/19
 * @Description:
 */

public class MapUtil implements Serializable {

    private Map<String,Object> columns;
    private Integer id;
    private Long batchId;
    private String tableName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<String, Object> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, Object> columns) {
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MapUtil{");
        sb.append("columns=").append(columns);
        sb.append(", tableName='").append(tableName).append('\'');
        sb.append(", batchId=").append(batchId);
        sb.append('}');
        return sb.toString();
    }
}
