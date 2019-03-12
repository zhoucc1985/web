package com.cloud.entity.vo.qualityReport;

import com.cloud.annotation.execl.ExcelResolve;
import com.cloud.common.enums.QualityConnectionReportStatusEnum;
import com.cloud.common.enums.QualityReportStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Ftp数据校验报告视图
 * @Author daituo
 * @Date 2019-1-17
 **/
public class FtpQualityReportVo implements Serializable {

    private Long id;

    /**
     * ftpId
     */
    private Long ftpId;
    /**
     * ftp_ip地址
     */
    @ExcelResolve(titleName = "FTP IP地址")
    private String ftpIp;

    /**
     * 路径
     */
    @ExcelResolve(titleName = "路径")
    private String path;

    /**
     * 用户名
     */
    @ExcelResolve(titleName = "用户名")
    private String userName;

    /**
     * 文件数
     */
    @ExcelResolve(titleName = "文件数")
    private Integer fileNum;

    /**
     * 空文件数
     */
    @ExcelResolve(titleName = "空文件数")
    private Integer emptyNum;

    /**
     * 非空文件数
     */
    @ExcelResolve(titleName = "非空文件数")
    private Integer noemptyNum;

    /**
     * 文件空间大小
     */
    @ExcelResolve(titleName = "文件空间大小")
    private String fileSize;

    /**
     * 空表率
     */
    @ExcelResolve(titleName = "空表率(%)")
    private String rate;

    /**
     * 1.已生成 2.生成中 3.生成失败 4.初始化
     */
    private Integer reportStatus;

    /**
     * 1.已连接 2.未连接
     */
    private Integer isConnection;

    @ExcelResolve(titleName = "报告状态")
    private String reportStatusDesc;

    @ExcelResolve(titleName = "连接状态")
    private String connectionStatusDesc;

    /**
     * 校验时间
     */
    @ExcelResolve(titleName = "数据校验时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date checkTime;

    public String getFtpIp() {
        return ftpIp;
    }

    public void setFtpIp(String ftpIp) {
        this.ftpIp = ftpIp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getConnectionStatusDesc() {
        if (Objects.isNull(this.isConnection)) {
            return null;
        }
        for (QualityConnectionReportStatusEnum qualityConnectionReportStatusEnum : QualityConnectionReportStatusEnum.values()) {
            if (qualityConnectionReportStatusEnum.getCode() == this.isConnection) {
                return qualityConnectionReportStatusEnum.getMessage();
            }
        }
        return null;
    }

    public void setConnectionStatusDesc(String connectionStatusDesc) {
        this.connectionStatusDesc = connectionStatusDesc;
    }

    public Integer getFileNum() {
        return fileNum;
    }

    public void setFileNum(Integer fileNum) {
        this.fileNum = fileNum;
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

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
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

    public Long getFtpId() {
        return ftpId;
    }

    public void setFtpId(Long ftpId) {
        this.ftpId = ftpId;
    }

    public Integer getIsConnection() {
        return isConnection;
    }

    public void setIsConnection(Integer isConnection) {
        this.isConnection = isConnection;
    }
}
