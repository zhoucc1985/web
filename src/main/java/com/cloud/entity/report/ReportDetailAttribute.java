package com.cloud.entity.report;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 报告详情属性实体
* @author huangYl
* @time 2018/11/15
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDetailAttribute implements Serializable {
    /**
    * id
    */
    @ApiModelProperty(notes = "id")
    private Long id;

    /**
    * 报告模板id
    */
    @ApiModelProperty(notes = "报告模板id")
    private Long reportTemplateId;

    /**
    * 报告模板页id
    */
    @ApiModelProperty(notes = "报告模板页id")
    private Long reportTemplatePageId;

    /**
    * 代码标识
    */
    @ApiModelProperty(notes = "代码标识")
    private String name;

    /**
    * 描述信息
    */
    @ApiModelProperty(notes = "描述信息")
    private String description;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", reportTemplateId=").append(reportTemplateId);
        sb.append(", reportTemplatePageId=").append(reportTemplatePageId);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}