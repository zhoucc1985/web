package com.cloud.entity.vo.qualityReport;

import com.cloud.annotation.execl.ExcelResolve;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
* 表信息实体类
* @author zhuJT
* @time 2019/01/11
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableInfoVo implements Serializable {
    /**
    * id
    */
    @ApiModelProperty(notes = "num")
    @ExcelResolve(titleName = "序号")
    private Long num;

    /**
    * 表名
    */
    @ApiModelProperty(notes = "表名")
    @ExcelResolve(titleName = "表名")
    private String tableName;

    /**
    * 表中文名（注释）
    */
    @ApiModelProperty(notes = "表中文名（注释）")
    @ExcelResolve(titleName = "表中文注释")
    private String tableCn;

    /**
    * 字段数
    */
    @ApiModelProperty(notes = "字段数")
    @ExcelResolve(titleName = "表字段数")
    private Integer fieldNum;

    /**
     * 空字段数
     */
    @ApiModelProperty(notes = "空字段数")
    @ExcelResolve(titleName = "空字段数")
    private Integer emptyNum;

    /**
     * 非空字段数
     */
    @ApiModelProperty(notes = "非空字段数")
    @ExcelResolve(titleName = "非空字段数")
    private Integer noEmptyNum;

    /**
    * 表数据量
    */
    @ApiModelProperty(notes = "表数据量")
    @ExcelResolve(titleName = "表记录数")
    private Long dataNum;

    /**
    * 数据空间
    */
    @ApiModelProperty(notes = "数据空间")
    @ExcelResolve(titleName = "表占用空间")
    private String dataSpace;

    /**
    * 数据源id
    */
    @ApiModelProperty(notes = "数据源id")
    private Long sourceId;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", num=").append(num);
        sb.append(", tableName=").append(tableName);
        sb.append(", tableCn=").append(tableCn);
        sb.append(", fieldNum=").append(fieldNum);
        sb.append(", dataNum=").append(dataNum);
        sb.append(", dataSpace=").append(dataSpace);
        sb.append(", sourceId=").append(sourceId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}