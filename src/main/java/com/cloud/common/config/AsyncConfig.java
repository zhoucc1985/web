package com.cloud.common.config;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * spring boot 线程连接池配置
 *
 * @author Pan jianneng
 * @time 2018/10/21 17:34
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    /**
     * 池满后每次添加线程个数
     */
    @Value("${async.executor.thread.core_pool_size}")
    private int corePoolSize;
    
    /**
     * 池最大线程个数
     */
    @Value("${async.executor.thread.max_pool_size}")
    private int maxPoolSize;

    /**
     * 同同存在最大
     */
    @Value("${async.executor.thread.queue_capacity}")
    private int queueCapacity;

    private String threadNamePrefix = "AsyncExecutorThread-";

    /**
     *
     * 自定义连接池属性
     *
     * @param
     * @return: java.util.concurrent.Executor
     * @auther: Pan jianneng
     * @date: 2018/10/21 22:06
     */
    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor() {
        return getExecutor();
    }

    private Executor getExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

}