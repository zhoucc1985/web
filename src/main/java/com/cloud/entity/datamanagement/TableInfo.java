package com.cloud.entity.datamanagement;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

import lombok.*;

/**
* 表信息实体类
* @author zhuJT
* @time 2019/01/11
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableInfo implements Serializable {
    /**
    * id
    */
    @ApiModelProperty(notes = "id")
    private Long id;

    /**
    * 表名
    */
    @ApiModelProperty(notes = "表名")
    private String tableName;

    /**
    * 表中文名（注释）
    */
    @ApiModelProperty(notes = "表中文名（注释）")
    private String tableCn;

    /**
    * 字段数
    */
    @ApiModelProperty(notes = "字段数")
    private Integer fieldNum;

    /**
     * 空字段数
     */
    @ApiModelProperty(notes = "空字段数")
    private Integer emptyNum;

    /**
    * 表数据量
    */
    @ApiModelProperty(notes = "表数据量")
    private Long dataNum;

    /**
    * 数据空间
    */
    @ApiModelProperty(notes = "数据空间")
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
        sb.append(", id=").append(id);
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