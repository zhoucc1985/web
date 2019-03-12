package com.cloud.shiro.filter;

import com.cloud.shiro.BasicControlFilterTool;
import com.cloud.shiro.LoginSessionCache;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Map;

/**
 * 访问权限拦截器
 *
 * @program cloudIAS
 * @author: Pan Jianneng
 * @create: 2018/11/21 09:34
 */
 @Component
public class CustomBasicHttpFilter extends BasicHttpAuthenticationFilter {

    /**
     * 用户在未登录的情况访问服务器，返回无权限
     * @author Pan Jianneng
     * @date 2018/11/21 1:18 PM
     * @param request
     * @param response
     * @return boolean
     */
    @Override
    protected boolean sendChallenge(ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setStatus(401);
        try {
            //通过subject获取登录会话信息
            Subject subject = this.getSubject(request, httpResponse);
            Map<String,Object> attributesMap = null;
            //当会话过期，退出登录，移除过期会话，如果不存在那么继续执行后面的操作
            Serializable sid = BasicControlFilterTool.getCookieSessionId(request);
            if (!subject.getSession().getId().equals(sid)
                    &&LoginSessionCache.isExpiration(sid)) {
                //会话过期，返回没有权限的状态码
                attributesMap = BasicControlFilterTool.getExpirationRedirectMap();
                LoginSessionCache.removeSession(sid);
                BasicControlFilterTool.issueForward(request,httpResponse,"/auth/login/time-out",attributesMap);
            }else{
                attributesMap = BasicControlFilterTool.getExpirationRedirectMap();
                BasicControlFilterTool.issueForward(request, httpResponse, "/auth/noLogin",attributesMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

