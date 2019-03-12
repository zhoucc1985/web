package com.cloud.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * mybatis插件插件运行原理：
 *      1.mybatis插件运行采用责任链模式，mybatis四大对象都会一一通过所有的拦截器责任链，每经过一次拦截器时，都会生成一个代理对象，因此有多少拦截器，就会生成
 *      多少代理对象
 *      2.mybatis运行时，代理对象调用方法时，会进入到代理对象invoke()方法里，invoke方法会调用interceptor.intercept()方法，然后invocation.proceed()方法
 *      会通过反射调用真实类的方法
 *
 * Mybatis日志记录拦截器，用于记录执行的SQL语句、参数以及耗时
 * @date 2018-12-23
 * @author daituo
 */
@Component
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class MybatisLogInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisLogInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //耗时
        long startTime = System.currentTimeMillis();
        //反射调用真实对象方法
        Object result = invocation.proceed();
        long cost = System.currentTimeMillis() - startTime;

        final Object[] args = invocation.getArgs();
        //获取真实的MappedStatement
        MappedStatement mappedStatement = (MappedStatement) args[0];
        //获取参数，if语句成立，表示sql语句有参数，参数格式是map形式
        Object parameter = "";
        if (args.length > 1) {
            parameter = args[1];
        }
        // 获取到节点的id,即sql语句的id
        String sqlId = mappedStatement.getId();
        // 获取sql语句
        String sql = mappedStatement.getBoundSql(parameter).getSql();
        LOGGER.info("SqlId:[{}]  Sql:[{}]  Params:[{}]  Cost:[{}ms]", sqlId, sql, JSON.toJSONString(parameter), cost);
        return result;
    }



    /**
     * 通过Plugin.wrap()生成代理对象
     * @param target 被代理对象
     * @return 代理对象
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {}
}
