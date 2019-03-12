package com.cloud.entity.datacollection;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 报告模板
* @author Pan jianneng
* @date 2018/11/14
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportTemplate implements Serializable {
    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private Long id;

    /**
    * 报告模板名称
    */
    @ApiModelProperty(notes = "报告模板名称")
    private String name;

    /**
    * logo图标
    */
    @ApiModelProperty(notes = "logo图标")
    private String logoPatch;

    /**
    * 背景音乐地址
    */
    @ApiModelProperty(notes = "背景音乐地址")
    private String bgmPath;

    /**
    * 报告图标备注
    */
    @ApiModelProperty(notes = "报告图标备注")
    private String logoRemark;

    /**
     * 是否是默认模板
     */
    @ApiModelProperty(notes = "是否是默认模板")
    private Boolean defaultTemplate;

    /**
     * 封面图片地址，使用的是模版页面中第一个页面的页面快照作为模板封面
     */
    @ApiModelProperty(notes = "封面图片地址")
    private String coverImgPath;

    /**
     * 报告模板页面
     */
    private List<ReportTemplatePage> pages = new ArrayList<ReportTemplatePage>();

    /**
     * 报告模板字段数据
     */
    private List<ReportTemplatePageField> fields = new ArrayList<ReportTemplatePageField>();

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", logoPatch=").append(logoPatch);
        sb.append(", bgmPath=").append(bgmPath);
        sb.append(", logoRemark=").append(logoRemark);
        sb.append(", defaultTemplate=").append(logoRemark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}