package com.cloud.shiro;

import com.cloud.common.vo.ShiroUser;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.*;

/**
 * 踢出操作
 *
 * @author Pan jianneng
 * @time 2018/10/19 14:47
 */
public class ShiroKickoutSessionControlFilter extends AccessControlFilter {

    /**
     * 踢出后到的地址
     */
    private String kickoutUrl;
    /**
     * 踢出之前登录的/之后登录的用户 默认踢出之前登录的用户
     */
    private boolean kickoutAfter = false;
    /**
     * 同一个帐号最大会话数 默认1
     */
    private int maxSession = 1;
    /**
     * 用于判断用户是否被踢出
     */
    private static final String KICK_OUT = "kickout";

    private SessionManager sessionManager;
    private Cache<String, Deque<Serializable>> cache;

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
    /**
     * 设置Cache的key的前缀
     */
    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("shiro_redis_cache");
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    /**
     * 将用户踢出逻辑
     * 拦截已登录用户访问
     * 从登录信息中获取用户会话信息，在从缓存中读取用户信息（通过登录用户名称），如果没有登录过，并且不再缓存队列中，就存入缓存队列中
     * 当队列中登录用户超出可同时登录次数，将最早的登录会话移除，加入新登录的客户端会话
     * 具体实现请看代码
     * @author Pan Jianneng
     * @date 2018/11/21 1:44 PM
     * @param request
     * @param response
     * @return boolean
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if(!subject.isAuthenticated() && !subject.isRemembered()) {
            //如果没有登录，直接进行之后的流程
            return true;
        }
        Session session = subject.getSession();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        String username = user.getLoginName();
        Serializable sessionId = session.getId();
        //读取缓存   没有就存入
        Deque<Serializable> deque = cache.get(username);
        //如果此用户没有session队列，也就是还没有登录过，缓存中没有
        //就new一个空队列，不然deque对象为空，会报空指针
        if(deque==null){
            deque = new LinkedList<Serializable>();
        }
        //如果队列里没有此sessionId，且用户没有被踢出；放入队列
        if(!deque.contains(sessionId) && session.getAttribute(KICK_OUT) == null) {
            //将sessionId存入队列
            deque.push(sessionId);
            //将用户的sessionId队列缓存
            cache.put(username, deque);
        }
        //如果队列里的sessionId数超出最大会话数，开始踢人
        while(deque.size() > maxSession) {
            Serializable kickoutSessionId = null;
            //如果踢出后者
            if(kickoutAfter) {
                kickoutSessionId = deque.removeFirst();
                //踢出后再更新下缓存队列
                cache.put(username, deque);
                //否则踢出前者
            } else {
                kickoutSessionId = deque.removeLast();
                //踢出后再更新下缓存队列
                cache.put(username, deque);
            }
            try {
                //获取被踢出的sessionId的session对象
                Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                if(kickoutSession != null) {
                    //设置会话的kickout属性表示踢出了
                    kickoutSession.setAttribute(KICK_OUT, true);
                }
            } catch (Exception e) {
            }
        }
        //如果被踢出了，直接退出，重定向到踢出后的地址
        if (session.getAttribute(KICK_OUT) != null) {
            //会话被踢出了
            try {
                //退出登录
                subject.logout();
            } catch (Exception e) {
            }
            HttpServletResponse httpResponse = WebUtils.toHttp(response);
            //被踢出状态码
            httpResponse.setStatus(401);
            BasicControlFilterTool.issueForward(request,response,kickoutUrl);
            return false;
        }
        return true;
    }

}