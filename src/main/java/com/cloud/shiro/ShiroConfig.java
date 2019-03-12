package com.cloud.shiro;

import com.cloud.common.dict.CommonDict;
import com.cloud.shiro.filter.CustomBasicHttpFilter;
import com.cloud.shiro.filter.CustomLogoutFilter;
import com.cloud.shiro.filter.CustomUserFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * shiro配置
 *
 * @author Pan jianneng
 * @time 2018/10/18 10:47
 */
@Configuration
public class ShiroConfig {

    @Value("${spring.redis.host}")
    public String host;

    @Value("${spring.redis.port}")
    public int port;

    @Value("${spring.redis.timeout}")
    public int timeout;

    @Value("${spring.redis.isRedisCache}")
    public int isRedisCache;

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 未授权界面; ----这个配置了没卵用，具体原因想深入了解的可以自行百度
        ////shiroFilterFactoryBean.setUnauthorizedUrl("/auth/403");
        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        //限制同一帐号同时在线的个数。
        filtersMap.put("kickout", kickoutSessionControlFilter());
        //所有URL权限拦截
        filtersMap.put("authcBasic",new CustomBasicHttpFilter());
        //登录用户信息拦截器，目前用户验证会话过期用
        filtersMap.put("user",new CustomUserFilter());
        //退出拦截
        filtersMap.put("logout",new CustomLogoutFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);
        // 权限控制map.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/report/**", "anon");
        filterChainDefinitionMap.put("/school/logo/**", "anon");
        filterChainDefinitionMap.put("/api/report/visit/**", "anon");
        filterChainDefinitionMap.put("/api/report/*/details", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/auth/login", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/auth/kickout", "anon");
        filterChainDefinitionMap.put("/my-websocket/**", "anon");
        filterChainDefinitionMap.put("/socketHandler/**", "anon");
        filterChainDefinitionMap.put("/topic/**", "anon");
        filterChainDefinitionMap.put("/**", "authcBasic,user,kickout");
    //    filterChainDefinitionMap.put("/**", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(@Qualifier("myShiroRealm") ShiroRealm myShiroRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(myShiroRealm);
        //注入记住我管理器
        securityManager.setRememberMeManager(rememberMeManager());
        if (useRedisCache()) {
            // 自定义缓存实现 使用redis
            securityManager.setCacheManager(redisCacheManager());
            // 自定义session管理 使用redis
            securityManager.setSessionManager(redisSessionManager());
        } else {
            //注入缓存管理器;
            //这个如果执行多次，也是同样的一个对象;
            securityManager.setCacheManager(ehCacheManager());
            securityManager.setSessionManager(ehSessionManager());
        }
        return securityManager;
    }

    /**
     * 密码校验规则HashedCredentialsMatcher
     * 这个类是为了对密码进行编码的 ,
     * 防止密码在数据库里明码保存 , 当然在登陆认证的时候 ,
     * 这个类也负责对form里输入的密码进行编码
     * 处理认证匹配处理器：如果自定义需要实现继承HashedCredentialsMatcher
     */
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName(CommonDict.HASH_ALGORITHM);
        //加密次数
        credentialsMatcher.setHashIterations(CommonDict.HASH_ITERATIONS);
        return credentialsMatcher;
    }

    /**
     * shiro缓存管理器;
     * 需要注入对应的其它的实体类中：
     * 1、安全管理器：securityManager
     * 可见securityManager是整个shiro的核心；
     *
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        System.out.println("ShiroConfiguration.getEhCacheManager()");
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache/ehcache-shiro.xml");
        return cacheManager;
    }

    /**
     * EnterpriseCacheSessionDAO shiro sessionDao层的实现；
     * 提供了缓存功能的会话维护，默认情况下使用MapCache实现，内部使用ConcurrentHashMap保存缓存的会话。
     */
    @Bean
    public EnterpriseCacheSessionDAO enterCacheSessionDAO() {
        EnterpriseCacheSessionDAO enterCacheSessionDAO = new EnterpriseCacheSessionDAO();
        //添加缓存管理器
        enterCacheSessionDAO.setCacheManager(ehCacheManager());
        //添加ehcache活跃缓存名称（必须和ehcache缓存名称一致）
        enterCacheSessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        return enterCacheSessionDAO;
    }

    /**
     * sessionManager添加session缓存操作DAO
     *
     * @param
     * @return: org.apache.shiro.web.session.mgt.DefaultWebSessionManager
     * @auther: Pan jianneng
     * @date: 2018/10/21 14:21
     */
    @Bean
    public DefaultWebSessionManager ehSessionManager() {
        ShiroSessionManager sessionManager = new ShiroSessionManager();
        sessionManager.setCacheManager(ehCacheManager());
        sessionManager.setSessionListeners(shiroSessionListener());
        sessionManager.setSessionDAO(enterCacheSessionDAO());
        Cookie cookie = sessionManager.getSessionIdCookie();
        cookie.setName("cloudIASSession");
        //默认是30分钟，1800000毫秒，这个是会话超时时间，跟缓存时间是不一样的要注意
        sessionManager.setGlobalSessionTimeout(1800000);
        return sessionManager;
    }


    /**
     * 身份认证realm;
     *
     * @return
     */
    @Bean("myShiroRealm")
    public ShiroRealm myShiroRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher) {
        ShiroRealm shiroRealm = new ShiroRealm();
        // 设置密码凭证匹配器
        shiroRealm.setCredentialsMatcher(matcher);
        return shiroRealm;
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     * 默认redis服务器为本地
     *
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        //这里设置跟全局会话时间一个样setGlobalSessionTimeout,单位秒
        redisManager.setExpire((timeout*60));
        return redisManager;
    }

    /**
     * Session Manager
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public DefaultWebSessionManager redisSessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionListeners(shiroSessionListener());
        sessionManager.setSessionDAO(redisSessionDAO());
        Cookie cookie = sessionManager.getSessionIdCookie();
        cookie.setName("cloudIASSession");
        sessionManager.setCacheManager(redisCacheManager());
        //全局会话超时时间（单位毫秒），设置为一小时
        sessionManager.setGlobalSessionTimeout(timeout*60*1000);
        //是否开启删除无效的session对象  默认为true
        sessionManager.setDeleteInvalidSessions(true);
        //是否开启定时调度器进行检测过期session 默认为true
        sessionManager.setSessionValidationSchedulerEnabled(true);
        //设置session失效的扫描时间, 清理用户直接关闭浏览器造成的孤立会话 默认为 1个小时
        //设置该属性 就不需要设置 ExecutorServiceSessionValidationScheduler 底层也是默认自动调用ExecutorServiceSessionValidationScheduler
        //// sessionManager.setSessionValidationInterval(10000);
        //取消url 后面的 JSESSIONID
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    @Bean
    public List<SessionListener> shiroSessionListener() {
        List<SessionListener> list = new ArrayList<SessionListener>();
        list.add(new ShiroSessionListener());
        return list;
    }

    /**
     * MyRedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * cookie对象;会话Cookie模板 ,默认为: JSESSIONID 问题: 与SERVLET容器名冲突,重新定义为sid或rememberMe，自定义
     *
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //setcookie的httponly属性如果设为true的话，会增加对xss防护的安全系数。它有以下特点：
        //设为true后，只能通过http访问，javascript无法访问
        //防止xss读取cookie
        simpleCookie.setHttpOnly(true);
        simpleCookie.setPath("/");
        //<!-- 记住我cookie生效时间7天 ,单位秒;-->
        simpleCookie.setMaxAge(604800);
        return simpleCookie;
    }

    /**
     * cookie管理对象;记住我功能,rememberMe管理器
     *
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    /**
     * FormAuthenticationFilter 过滤器 过滤记住我
     *
     * @return
     */
    @Bean
    public FormAuthenticationFilter formAuthenticationFilter() {
        FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
        //对应前端的checkbox的name = rememberMe
        formAuthenticationFilter.setRememberMeParam("rememberMe");
        return formAuthenticationFilter;
    }


    /**
     * 限制同一账号登录同时登录人数控制
     *
     * @return
     */
    @Bean
    public ShiroKickoutSessionControlFilter kickoutSessionControlFilter() {
        ShiroKickoutSessionControlFilter shiroKickoutSessionControlFilter = new ShiroKickoutSessionControlFilter();
        cacheTypeManager(shiroKickoutSessionControlFilter);
        shiroKickoutSessionControlFilter.setKickoutAfter(false);
        // TODO zengqh 修改同一账号同时在线人数为10个人
        shiroKickoutSessionControlFilter.setMaxSession(10);
        shiroKickoutSessionControlFilter.setKickoutUrl("/auth/kickout");
        return shiroKickoutSessionControlFilter;
    }

    /**
     * 根据使用的缓存方式进行缓存管理设置
     * @param shiroKickoutSessionControlFilter
     * @return
     */
    private void cacheTypeManager(ShiroKickoutSessionControlFilter shiroKickoutSessionControlFilter) {
        if (useRedisCache()) {
            shiroKickoutSessionControlFilter.setCacheManager(redisCacheManager());
            shiroKickoutSessionControlFilter.setSessionManager(redisSessionManager());
        } else {
            shiroKickoutSessionControlFilter.setCacheManager(ehCacheManager());
            shiroKickoutSessionControlFilter.setSessionManager(ehSessionManager());
        }
    }


    /***
     * 授权所用配置
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /***
     * 使授权注解起作用不如不想配置可以在pom文件中加入
     * <dependency>
     *<groupId>org.springframework.boot</groupId>
     *<artifactId>spring-boot-starter-aop</artifactId>
     *</dependency>
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * Shiro生命周期处理器
     * 此方法不添加为静态方法，将获取不到配置文件的属性值
     */
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 使用redis管理缓存
     * @return
     */
    private boolean useRedisCache(){
        return isRedisCache==0;
    }

}