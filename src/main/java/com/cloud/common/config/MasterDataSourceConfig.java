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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 1.多数据源配置时，必须要有一个主数据源，即 MasterDataSourceConfig 配置
 * 2.@Primary 标志这个 Bean 如果在多个同类 Bean 候选时，该 Bean 优先被考虑。
 * 3.多数据源配置的时候注意，必须要有一个主数据源，用 @Primary 标志该 Bean
 * 4.@MapperScan 扫描 Mapper 接口，包路径精确到 master，为了和下面 second 数据源做到精确区分
 *
 * @Author daituo
 * @Date 2019-1-24
 **/
@SpringBootConfiguration
@MapperScan(basePackages = MasterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig {

    static final String PACKAGE = "com.cloud.mapper.master.*";
    static final String MAPPER_LOCATION = "classpath:mapper/master/**/*.xml";

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${master.datasource.url}")
    private String url;

    @Value("${master.datasource.username}")
    private String user;

    @Value("${master.datasource.password}")
    private String password;

    @Value("${master.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "masterDataSource")
    @Primary
    public DataSource masterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MasterDataSourceConfig.MAPPER_LOCATION));

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
