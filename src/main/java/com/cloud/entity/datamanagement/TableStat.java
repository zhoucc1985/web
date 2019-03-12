package com.cloud.entity.datamanagement;

import com.cloud.annotation.execl.ExcelResolve;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

import lombok.*;

/**
* 统计表信息实体类
* @author zhuJT
* @time 2019/01/11
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableStat implements Serializable {
    /**
    * id
    */
    @ApiModelProperty(notes = "id")
    private Long id;

    /**
    * 数据源id
    */
    @ApiModelProperty(notes = "数据源id")
    private Long sourceId;

    /**
    * 表数量
    */
    @ApiModelProperty(notes = "表数量")
    @ExcelResolve(titleName = "表数量")
    private Integer tableNum;

    /**
    * 空表数量
    */
    @ApiModelProperty(notes = "空表数量")
    @ExcelResolve(titleName = "空表数量")
    private Integer emptyNum;

    /**
    * 非空表数量
    */
    @ApiModelProperty(notes = "非空表数量")
    @ExcelResolve(titleName = "非空表数量")
    private Integer noemptyNum;

    /**
    * 数据空间占用量
    */
    @ApiModelProperty(notes = "数据空间占用量")
    @ExcelResolve(titleName = "数据空间占用量")
    private String space;

    /**
    * 空表率
    */
    @ApiModelProperty(notes = "空表率")
    @ExcelResolve(titleName = "空表率")
    private String rate;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sourceId=").append(sourceId);
        sb.append(", tableNum=").append(tableNum);
        sb.append(", emptyNum=").append(emptyNum);
        sb.append(", noemptyNum=").append(noemptyNum);
        sb.append(", space=").append(space);
        sb.append(", rate=").append(rate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}