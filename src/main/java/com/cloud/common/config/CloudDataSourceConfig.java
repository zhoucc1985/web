package com.cloud.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 云端数据源配置
 *
 * @Author zengqh
 * @Date 2019-2-13
 **/
@SpringBootConfiguration
@MapperScan(basePackages = CloudDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "cloudSqlSessionFactory")
public class CloudDataSourceConfig {

    static final String PACKAGE = "com.cloud.mapper.cloud.*";
    private static final String MAPPER_LOCATION = "classpath:mapper/cloud/**/*.xml";

    @Autowired
    private ApplicationContext applicationContext;

     @Value("${cloud.datasource.driverClassName}")
     private String driverClass;
    @Value("${cloud.datasource.url}")
    private String url;
    @Value("${cloud.datasource.username}")
    private String user;
    @Value("${cloud.datasource.password}")
    private String password;

    @Bean(name = "cloudDataSource")
    public DataSource realDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "cloudTransactionManager")
    public DataSourceTransactionManager clusterTransactionManager() {
        return new DataSourceTransactionManager(realDataSource());
    }

    @Bean(name = "cloudSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("cloudDataSource") DataSource cloudDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(cloudDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(CloudDataSourceConfig.MAPPER_LOCATION));

        //mybatis的Configuration配置类
        Configuration configuration = new Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCallSettersOnNulls(true);
        sessionFactory.setConfiguration(configuration);

        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");   //设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用
        p.setProperty("rowBoundsWithCount", "true"); //设置为true时，使用RowBounds分页会进行count查询
        p.setProperty("reasonable", "true");    //启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
        pageHelper.setProperties(p);

        //定义mybatis的插件Plugins
        Interceptor[] plugins = new Interceptor[]{(Interceptor) applicationContext.getBean("mybatisLogInterceptor"), pageHelper};
        sessionFactory.setPlugins(plugins);
        return sessionFactory.getObject();
    }
}
