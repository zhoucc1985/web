package com.cloud.common.listener;

import com.cloud.service.report.ReportVcFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 报告访问监听器
 *
 * @author huangYl
 * @date 2018/11/29
 **/
@WebListener
public class ReportVisitListener implements ServletContextListener {

    @Autowired
    private ReportVcFactory reportVcFactory;

    /**
     * 项目启动时加载完所有Bean时初始化触发
     *
     * @param servletContextEvent servletContext事件
     * @return void
     * @author huangYl
     * @date 2018/11/29 11:34
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        reportVcFactory.getInstance().updateCacheDataAndReportVisitCount();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
