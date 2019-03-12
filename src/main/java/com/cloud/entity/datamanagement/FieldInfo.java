package com.cloud.entity.datamanagement;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 请添加描述
* @author zhuJT
* @time 2019/01/11
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldInfo implements Serializable {
    /**
    * id
    */
    @ApiModelProperty(notes = "id")
    private Long id;

    /**
    * 序号
    */
    @ApiModelProperty(notes = "序号")
    private Integer serNum;

    /**
    * 表字段
    */
    @ApiModelProperty(notes = "表字段")
    private String tableField;

    /**
    * 字段描述
    */
    @ApiModelProperty(notes = "字段描述")
    private String fieldDesc;

    /**
    * 非空数据量
    */
    @ApiModelProperty(notes = "非空数据量")
    private Long dataNum;

    /**
    * 表id
    */
    @ApiModelProperty(notes = "表id")
    private Long tableId;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", serNum=").append(serNum);
        sb.append(", tableField=").append(tableField);
        sb.append(", fieldDesc=").append(fieldDesc);
        sb.append(", dataNum=").append(dataNum);
        sb.append(", tableId=").append(tableId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}