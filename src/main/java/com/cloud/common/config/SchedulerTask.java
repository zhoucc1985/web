package com.cloud.common.config;

import com.cloud.service.datacollection.AsyncExecutorCommonService;
import com.cloud.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务配置，所有定时任务入口
 *
 * @author: Pan Jianneng
 * @create: 2018/11/23 10:58
 */
// @Component
public class SchedulerTask {
    @Autowired
    private AsyncExecutorCommonService asyncExecutorCommonService;

    @Autowired
    private ReportService reportService;

    /**
     * 定时校验采集数据原数据
     * 每天凌晨1点执行一次
     *
     * @return void
     * @author Pan Jianneng
     * @date 2018/11/23 11:04 AM
     */
    //@Scheduled(cron = "0 0 1 * * ?")
    public void checkCollectionDataSourceJob() {
        asyncExecutorCommonService.automaticSyncHandleData();
    }

    /**
     * 从数据库读取数据更新ServletContext内存中的数据，和更新报告中的访问次数
     * 每两小时执行一次
     *
     * @author huangYl
     * @date 2018/11/29 10:29
     */
    //@Scheduled(cron = "0 0 0/2 * * ?")
    public void updateCacheDataAndReportVisitCount() {
        reportService.updateCacheDataAndReportVisitCount();
    }
}

