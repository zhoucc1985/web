package com.cloud.entity.datacollection;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 报告数据批次
* @author Pan jianneng
* @date 2018/11/14
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectionBatch implements Serializable {
    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private Long id;

    /**
    * 报告名称，自动生成
    */
    @ApiModelProperty(notes = "报告名称，自动生成")
    private String reportName;

    /**
    * 批次号
    */
    @ApiModelProperty(notes = "批次号")
    private String batchNo;

    /**
    * 组织机构id
    */
    @ApiModelProperty(notes = "组织机构id")
    private Long orgId;

    /**
     * 学校名称
     */
    @ApiModelProperty(notes = "组织机构名称，学校名称")
    private String schoolName;

    /**
     * 组织机构号
     */
    @ApiModelProperty(notes = "组织机构号")
    private String orgNumber;

    /**
    * 导入时间
    */
    @ApiModelProperty(notes = "导入时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date importTime;

    /**
    * 报告模板id
    */
    @ApiModelProperty(notes = "报告模板id")
    private Long rtId;

    /**
    * 导入的文档总数据
    */
    @ApiModelProperty(notes = "导入的文档总数据")
    private Integer totalNumber;

    /**
    * 有效数据数
    */
    @ApiModelProperty(notes = "有效数据数")
    private Integer validNumber;

    /**
    * 无效数据数
    */
    @ApiModelProperty(notes = "无效数据数")
    private Integer invalidNumber;

    /**
    * CHECKED：校验完成；CHECKING：校验中；IMPORTING：数据导入中
    */
    @ApiModelProperty(notes = "CHECKED：校验完成；CHECKING：校验中；IMPORTING：数据导入中")
    private String status;

	/**
	 *
     */
    //@Transient
    @ApiModelProperty(notes = "关联模板名称")
    private String templateName;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", reportName=").append(reportName);
        sb.append(", batchNo=").append(batchNo);
        sb.append(", orgId=").append(orgId);
        sb.append(", importTime=").append(importTime);
        sb.append(", rtId=").append(rtId);
        sb.append(", totalNumber=").append(totalNumber);
        sb.append(", validNumber=").append(validNumber);
        sb.append(", invalidNumber=").append(invalidNumber);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}