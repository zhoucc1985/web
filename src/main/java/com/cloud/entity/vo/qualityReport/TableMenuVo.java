package com.cloud.entity.vo.qualityReport;

import com.cloud.entity.datamanagement.TableInfo;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统下包含的table视图
 * @Author daituo
 * @Date 2019-1-17
 **/
public class TableMenuVo {

    /**
     * 数据库数据源Id
     */
    private Long id;

    /**
     * 系统名
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String systemName;

    /**
     * 表总数
     */
    private Integer tableCount;

    /**
     * tables
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TableInfo> tableInfoList = new ArrayList<>();

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public List<TableInfo> getTableInfoList() {
        return tableInfoList;
    }

    public void setTableInfoList(List<TableInfo> tableInfoList) {
        this.tableInfoList = tableInfoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTableCount() {
        return tableCount;
    }

    public void setTableCount(Integer tableCount) {
        this.tableCount = tableCount;
    }

}
