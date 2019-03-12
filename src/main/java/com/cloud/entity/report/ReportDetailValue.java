package com.cloud.entity.report;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 报告详情属性值实体
* @author huangYl
* @time 2018/11/15
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDetailValue implements Serializable {
    /**
    * id
    */
    @ApiModelProperty(notes = "id")
    private Long id;

    /**
    * 报告id
    */
    @ApiModelProperty(notes = "报告id")
    private Long reportId;

    /**
    * 报告属性id
    */
    @ApiModelProperty(notes = "报告属性id")
    private Long reportDetailAttributeId;

    /**
    * 属性值
    */
    @ApiModelProperty(notes = "属性值")
    private String value;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", reportId=").append(reportId);
        sb.append(", reportDetailAttributeId=").append(reportDetailAttributeId);
        sb.append(", value=").append(value);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}