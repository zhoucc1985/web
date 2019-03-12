package com.cloud.common.vo.report;

import com.cloud.entity.report.Report;
import lombok.Data;

/**
 * 报告返回实体
 *
 * @author huangYl
 * @date 2018/11/15 20:37
 **/
@Data
public class ReportVo extends Report{
    /**
     * 报告名称
     */
    private String repName;
    /**
     * 批次号
     */
    private String batchNo;
    /**
     * 学校机构编码
     */
    private String schoolCode;
    /**
     * 学校名称
     */
    private String schoolName;
}
