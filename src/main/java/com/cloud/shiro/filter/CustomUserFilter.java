package com.cloud.shiro.filter;

import com.cloud.common.vo.ShiroUser;
import com.cloud.shiro.BasicControlFilterTool;
import com.cloud.shiro.LoginSessionCache;
import com.cloud.shiro.LoginShiroCache;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Map;

/**
 * 用户登录会话状态拦截器，判断用户会话是否过期处理，过期跳转到登录页面或给出需要登录提示
 *
 * @program cloudIAS
 * @author: Pan Jianneng
 * @create: 2018/11/21 11:02
 */
@Component
public class CustomUserFilter extends UserFilter {
    private static final Logger log = LoggerFactory.getLogger(CustomUserFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (this.isLoginRequest(request, response)) {
            return true;
        } else {
            //当前请求信息
            Subject subject = this.getSubject(request, response);
            //这个是登录用户缓存，缓存中没有数据
            ShiroUser user = LoginShiroCache.getShiroUserBySessionId(subject.getSession().getId());
            return subject.getPrincipal() != null|| user!=null;
        }
    }

    /**
     * 会话拦截，会话过期返回登录界面
     * @author Pan Jianneng
     * @date 2018/11/21 1:13 PM
     * @param request
     * @param response
     * @return boolean
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //通过subject获取登录会话信息
        Subject subject = this.getSubject(request, response);
        Map<String,Object> attributesMap = null;
        //当会话过期，退出登录，移除过期会话，如果不存在那么继续执行后面的操作
        Serializable sid = BasicControlFilterTool.getCookieSessionId(request);
        if (!subject.getSession().getId().equals(sid)
                &&LoginSessionCache.isExpiration(sid)) {
            //会话过期，返回没有权限的状态码
            HttpServletResponse httpResponse = WebUtils.toHttp(response);
            httpResponse.setStatus(401);
            attributesMap = BasicControlFilterTool.getExpirationRedirectMap();
            LoginSessionCache.removeSession(sid);
            BasicControlFilterTool.issueForward(request,response,"/auth/login/time-out",attributesMap);
        }

        return false;
    }
}

