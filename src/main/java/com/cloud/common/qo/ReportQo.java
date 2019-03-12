package com.cloud.common.qo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 报告查询对象
 *
 * @author huangYl
 * @date 2018/11/15
 **/
@Data
public class ReportQo extends BaseQo {
    /**
     * 开始时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    /**
     * 报告生成状态(未生成：NOMINATED，生成成功：SUCCESS，正在生成：GENERATING，生成失败：FAIL)
     */
    private String status;
    /**
     * 组织机构类型
     */
    private String orgCode;

    /**
     * 关键字
     */
    private String keyword;
}
