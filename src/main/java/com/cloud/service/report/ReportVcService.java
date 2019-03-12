package com.cloud.service.report;

import com.cloud.common.vo.report.ReportVo;

import java.util.List;

/**
 * 报告访问次数接口
 *
 * @author huangYl
 * @date 2018/12/13
 */
public interface ReportVcService {
    /**
     * 统计报告详情访问次数
     *
     * @param reportId 报告id
     * @param clientIp 客户端ip
     * @author huangYl
     */
    void selectCountReportVisits(Long reportId, String clientIp);

    /**
     * 用于定时器从数据库读取数据更新内存中的数据，和更新报告中的访问次数
     *
     * @author huangYl
     */
    void updateCacheDataAndReportVisitCount();

    /**
     * 查询报告列表时，校验内存中是否存在报告的访问次数，如果有设置报告访问次数
     *
     * @param list 报告列表
     * @return void
     * @author huangYl
     */
    void verifyReportListByMemory(List<ReportVo> list);

    /**
     * 根据报告ID删除内存中的浏览记录
     *
     * @param reportId 报告id
     * @return void
     * @author huangYl
     */
    void deleteByReportId(Long reportId);


}
