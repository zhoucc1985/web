package com.cloud.service.report;

import com.cloud.service.report.impl.ReportVcMemoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 报告访问次数工厂
 *
 * @author huangYl
 * @date 2018/12/13 9:44
 **/
@Component
public class ReportVcFactory {

    @Value("${spring.redis.isRedisCache}")
    public int isRedisCache;

    @Autowired
    private ReportVcMemoryServiceImpl reportVcMemoryService;

    public ReportVcService getInstance() {
        // 判断是否使用redis作缓存
        if (0 == isRedisCache) {
            return reportVcMemoryService;
        } else {
            return reportVcMemoryService;
        }
    }
}
