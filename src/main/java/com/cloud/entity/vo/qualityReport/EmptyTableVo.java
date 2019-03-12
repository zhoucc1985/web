package com.cloud.entity.vo.qualityReport;

import com.cloud.annotation.execl.ExcelResolve;

/**
 * 空表视图
 * @Author zjt
 * @Date 2019-02-19
 **/
public class EmptyTableVo {
    /**
     * 序号
     */
    @ExcelResolve(titleName = "序号")
    private Integer num;

    /**
     * 表名称
     */
    @ExcelResolve(titleName = "表名称")
    private String tableName;

    /**
     * 表中文名
     */
//    @ExcelResolve(titleName = "表中文名")
    private String tableCn;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableCn() {
        return tableCn;
    }

    public void setTableCn(String tableCn) {
        this.tableCn = tableCn;
    }
}
