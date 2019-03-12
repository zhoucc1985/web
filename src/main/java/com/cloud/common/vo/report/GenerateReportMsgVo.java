package com.cloud.common.vo.report;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 生成报告信息实体
 *
 * @author huangYl
 * @date 2018/11/24
 **/
@Data
@AllArgsConstructor
public class GenerateReportMsgVo {

    /**
     * 批次ID
     */
    private Long batchId;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 总生成数据数目
     */
    private Integer generateNumber;

    /**
     * 报告id
     */
    private Long reportId;
}
