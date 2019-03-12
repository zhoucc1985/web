package com.cloud.common.config;

import com.cloud.service.datacollection.AsyncExecutorCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * 启动服务后自动对原数据进行检验检查，并对原数据进行标准化入库
 * 这个只是防止后台服务器断开之后，重新对原数据进行处理，几率很小，不代表没有
 * @program cloudIAS
 * @author: Pan Jianneng
 * @create: 2018/11/17 19:49
 */
//@Component
//@Order(value = 2)
public class StartupRunner  implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(StartupRunner.class);

    @Autowired
    private AsyncExecutorCommonService asyncExecutorCommonService;

    @Override
    public void run(String... args) {
        asyncExecutorCommonService.automaticSyncHandleData();
    }

}

