package com.cloud.entity.datacollection;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 报告模板页面
* @author Pan jianneng
* @date 2018/11/14
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportTemplatePage implements Serializable {
    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private Long id;

    /**
    * 页面编码，排序
    */
    @ApiModelProperty(notes = "页面编码，排序")
    private String code;

    /**
    * 报告模板id
    */
    @ApiModelProperty(notes = "报告模板id")
    private Long rtId;

    /**
    * 页面快照，图片地址
    */
    @ApiModelProperty(notes = "页面快照，图片地址")
    private String pageSnapPath;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", rtId=").append(rtId);
        sb.append(", pageSnapPath=").append(pageSnapPath);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}