package com.cloud.entity.vo.qualityReport;

import com.cloud.entity.datamanagement.FileInfo;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Ftp详情视图
 * @Author daituo
 * @Date 2019-1-17
 **/
public class FtpDetailVo extends FileInfo {

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifiedTime;

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
