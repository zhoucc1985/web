package com.cloud.shiro.filter;

import com.cloud.shiro.BasicControlFilterTool;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.Locale;

/**
 * 退出拦截器
 *
 * @program cloudIAS
 * @author: Pan Jianneng
 * @create: 2018/11/21 09:56
 */
public class CustomLogoutFilter extends LogoutFilter {

    private static final Logger log = LoggerFactory.getLogger(CustomLogoutFilter.class);

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = this.getSubject(request, response);
        String post = "POST";
        if (this.isPostOnlyLogout() && !post.equals(WebUtils.toHttp(request).getMethod().toUpperCase(Locale.ENGLISH))) {
            return this.onLogoutRequestNotAPost(request, response);
        } else {
            try {
                Serializable sid = subject.getSession().getId();
                subject.logout();
            } catch (SessionException var6) {
                log.debug("退出异常！", var6);
            }
            try {
                BasicControlFilterTool.issueForward(request, response, "/auth/logout/fail");
            } catch (Exception e) {
                log.error("退出成功，返回消息异常！"+e);
            }
            return false;
        }
    }

}

