package com.cloud.entity.vo.qualityReport;

import com.cloud.annotation.execl.ExcelResolve;
import com.cloud.common.enums.QualityReportStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Objects;

/**
 * 数据库校验报告视图
 * @Author daituo
 * @Date 2019-1-17
 **/
public class DbQualityReportVo {

    private Long id;

    /**
     * 数据源Id
     */
    private Long dataSourceId;
    /**
     * 系统名称
     */
    @ExcelResolve(titleName = "系统名称")
    private String systemName;

    /**
     * 表数量
     */
    @ExcelResolve(titleName = "表数量")
    private Integer tableNum;

    /**
     * 空表数量
     */
    @ExcelResolve(titleName = "空表数量")
    private Integer emptyNum;

    /**
     * 非空表数量
     */
    @ExcelResolve(titleName = "非空表数量")
    private Integer noemptyNum;

    /**
     * 数据空间占用量
     */
    @ExcelResolve(titleName = "数据空间占用量")
    private String space;

    /**
     * 空表率
     */
    @ExcelResolve(titleName = "空表率(%)")
    private String rate;

    /**
     * 报告生成状态 1.已生成 2.生成中 3.生成失败 4.初始华
     *              除了1.已生成 其余都为生成中
     */
    private Integer reportStatus;

    @ExcelResolve(titleName = "报告状态")
    private String reportStatusDesc;
    /**
     * 校验时间
     */
    @ExcelResolve(titleName = "数据校验时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date checkTime;

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public Integer getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getReportStatusDesc() {
        if (Objects.isNull(this.reportStatus)) {
            return null;
        }
        for (QualityReportStatusEnum qualityReportStatusEnum : QualityReportStatusEnum.values()) {
            if (qualityReportStatusEnum.getCode() == this.reportStatus) {
                return qualityReportStatusEnum.getMessage();
            }
        }
        return null;
    }

    public void setReportStatusDesc(String reportStatusDesc) {
        this.reportStatusDesc = reportStatusDesc;
    }

    public Integer getTableNum() {
        return tableNum;
    }

    public void setTableNum(Integer tableNum) {
        this.tableNum = tableNum;
    }

    public Integer getEmptyNum() {
        return emptyNum;
    }

    public void setEmptyNum(Integer emptyNum) {
        this.emptyNum = emptyNum;
    }

    public Integer getNoemptyNum() {
        return noemptyNum;
    }

    public void setNoemptyNum(Integer noemptyNum) {
        this.noemptyNum = noemptyNum;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(Long dataSourceId) {
        this.dataSourceId = dataSourceId;
    }
}
