package com.cloud.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.core.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志拦截器
 * @date 2018-12-23
 * @author daituo
 */
@Component
public class ApiCostInterceptor implements HandlerInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(ApiCostInterceptor.class);
    
    /**
     * ThreadLocal用法：
     * 当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本
     * ThreadLocal底层存储依靠map<threadname,value>集合，即每个单独的线程有独立的变量副本
     */
    private static final ThreadLocal<Long> startTime = new ThreadLocal<>();
    
    /**
     * 请求响应后
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex) {
        // 请求耗时
        int cost = (int)(System.currentTimeMillis() - startTime.get());
        startTime.remove();
        LOG.info("Request URL:[{}]  Time:[{}ms]" ,request.getRequestURL(),cost);
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView modelAndView) {
    }
    
    /**
     * 请求调用前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj)
        throws Exception {
        startTime.set(System.currentTimeMillis());
        if (StringUtils.contains(request.getContentType(), "json") && request.getParameterMap().size() == 0) {
            LOG.info("Request URL:[{}]   Params:{}", request.getRequestURL().toString(), IOUtils.toString(request.getReader()));
        } else {
            LOG.info("Request URL:[{}]   Params:{}", request.getRequestURL().toString(), JSON.toJSON(request.getParameterMap()));
        }
        return true;
    }
}