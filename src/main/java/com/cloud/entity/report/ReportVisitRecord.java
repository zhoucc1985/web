package com.cloud.entity.report;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 报告详情访问记录实体
 *
 * @author huangYl
 * @time 2018/11/27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportVisitRecord implements Serializable {
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
     * ip地址
     */
    @ApiModelProperty(notes = "ip地址")
    private String ipAddress;

    private static final long serialVersionUID = 1L;

    /**
     * 构造器
     *
     * @param reportId  报告id
     * @param ipAddress ip地址
     */
    public ReportVisitRecord(Long reportId, String ipAddress) {
        this.reportId = reportId;
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", reportId=").append(reportId);
        sb.append(", ipAddress=").append(ipAddress);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}