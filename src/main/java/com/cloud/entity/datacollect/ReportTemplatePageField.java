package com.cloud.entity.datacollect;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
* 报告模板页面字段
* @author Pan jianneng
* @date 2018/11/14
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportTemplatePageField implements Serializable {
    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private Long id;

    /**
    * 所属页面id
    */
    @ApiModelProperty(notes = "所属页面id")
    private Long rtpId;

    /**
    * 字段编码
    */
    @ApiModelProperty(notes = "字段编码")
    private String code;

    /**
    * 字段名称
    */
    @ApiModelProperty(notes = "字段名称")
    private String name;

    /**
    * 这个是为了方便查询
    */
    @ApiModelProperty(notes = "这个是为了方便查询")
    private Long rtId;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", rtpId=").append(rtpId);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", rtId=").append(rtId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}