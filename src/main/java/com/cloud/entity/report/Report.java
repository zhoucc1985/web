package com.cloud.entity.report;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 报告实体类
* @author huangYl
* @time 2018/11/15
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report implements Serializable {
    /**
     * 报告状态(未生成：NOMINATED，生成成功：SUCCESS，正在生成：GENERATING，生成失败：FAIL)
     */
    public static final String REPORT_STATUS_NOMINATED = "NOMINATED";
    public static final String REPORT_STATUS_SUCCESS = "SUCCESS";
    public static final String REPORT_STATUS_GENERATING = "GENERATING";
    public static final String REPORT_STATUS_FAIL = "FAIL";

    /**
    * id
    */
    @ApiModelProperty(notes = "id")
    private Long id;

    /**
    * 批次id
    */
    @ApiModelProperty(notes = "批次id")
    private Long batchId;

    /**
    * 总生成数据数目
    */
    @ApiModelProperty(notes = "总生成数据数目")
    private Integer generateNumber = 0;

    /**
    * 未生成：NOMINATED，生成成功：SUCCESS，正在生成：GENERATING，生成失败：FAIL
    */
    @ApiModelProperty(notes = "未生成：NOMINATED，生成成功：SUCCESS，正在生成：GENERATING，生成失败：FAIL")
    private String generateStatus;

    /**
    * 可以再生成：1，不需要再生成：0
    */
    @ApiModelProperty(notes = "可以再生成：1，不需要再生成：0")
    private Byte generateAgain;

    /**
    * 最后一次生成时间
    */
    @ApiModelProperty(notes = "最后一次生成时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date generateTime;

    /**
    * 备注
    */
    @ApiModelProperty(notes = "备注")
    private String remark;

    /**
    * 模板id
    */
    @ApiModelProperty(notes = "模板id")
    private Long rtId;

    /**
    * 组织机构id
    */
    @ApiModelProperty(notes = "组织机构id")
    private Long orgId;

    /**
    * 报告链接
    */
    @ApiModelProperty(notes = "报告链接")
    private String reportLink;

    /**
    * 浏览人数
    */
    @ApiModelProperty(notes = "浏览人数")
    private Integer visitCount;

    /**
     * 学校logo
     */
    @ApiModelProperty(notes = "学校logo")
    private String schoolLogoPath;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", batchId=").append(batchId);
        sb.append(", generateNumber=").append(generateNumber);
        sb.append(", generateStatus=").append(generateStatus);
        sb.append(", generateAgain=").append(generateAgain);
        sb.append(", generateTime=").append(generateTime);
        sb.append(", remark=").append(remark);
        sb.append(", rtId=").append(rtId);
        sb.append(", orgId=").append(orgId);
        sb.append(", reportLink=").append(reportLink);
        sb.append(", visitCount=").append(visitCount);
        sb.append(", schoolLogoPath=").append(schoolLogoPath);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}