package com.cloud.entity.vo.qualityReport;

import com.cloud.entity.datamanagement.FieldInfo;

/**
 * 字段详情VO
 * @Author daituo
 * @Date 2019-1-17
 **/
public class FieldDetailVo extends FieldInfo {

    /**
     * 总记录数
     */
    private Long totalRecords;

    /**
     * 非空率
     */
    private Double noEmptyRatio;

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Double getNoEmptyRatio() {
        return noEmptyRatio;
    }

    public void setNoEmptyRatio(Double noEmptyRatio) {
        this.noEmptyRatio = noEmptyRatio;
    }
}
