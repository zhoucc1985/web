package com.cloud.controller.loggin;

import com.cloud.common.utils.CommonUtils;
import com.cloud.common.utils.IpUtils;
import com.cloud.common.utils.ObjectUtils;
import com.cloud.common.utils.UserUtils;
import com.cloud.common.vo.ShiroUser;
import com.cloud.shiro.LoginShiroCache;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 添加文件描述
 *
 * @author Pan jianneng
 * @time 2018/10/20 15:45
 */
@Controller
@RequestMapping(value = "/auth")
@ResponseBody
public class LoginController {

    /**
     * webSocket后端端口
     */
    @Value("${server.port}")
    private String serverPort;

    /**
     * 没有登录的情况下返回信息给前台，需要登录
     *
     * @return:
     * @auther: Pan jianneng
     * @date: 2018/10/20 16:10
     */
    @RequestMapping(value = "/noLogin")
    public Map<String, Object> nologinErrorMessage(HttpServletRequest request, HttpServletResponse response) {
        return CommonUtils.getResultMap("您未登录，请登录","noSession");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> submitLogin(String username, String password, HttpServletRequest request, boolean rememberMe) {
        Map<String, Object> resultMap = CommonUtils.getSucResultMap();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            if (rememberMe) {
                token.setRememberMe(true);
            }
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            ShiroUser user = (ShiroUser) subject.getPrincipal();
            String wsUrl = IpUtils.getCurrentServerIpAddress()==""?null:IpUtils.getCurrentServerIpAddress()+":"+serverPort;
            resultMap.put("ws_url",wsUrl);
            resultMap.put("datas", user);
        } catch (DisabledAccountException e) {
            resultMap = CommonUtils.getErrorResultMap("账号已被禁用");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            resultMap = CommonUtils.getErrorResultMap("用户名或密码错误");
        }
        return resultMap;
    }

    /**
     * 测试接口
     *
     * @return:
     * @auther: Pan jianneng
     * @date: 2018/10/20 16:10
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public Map<String, Object> loginSuccessMessage(HttpServletRequest request) {
        Map<String, Object> resultMap = CommonUtils.getSucResultMap();
        ShiroUser currentLoginUser = UserUtils.getInstance().getCurrentlyUserInfo();

        if (currentLoginUser != null && StringUtils.isNotEmpty(currentLoginUser.getLoginName())) {
            resultMap.put("datas", currentLoginUser);
        } else {
            resultMap = CommonUtils.getResultMap("您未登录,请登录","noSession");
        }
        return resultMap;
    }

    @RequestMapping(value = "/kickout")
    public Map<String, Object> kickOut() {
        return CommonUtils.getResultMap("您已在其他浏览器登录，请重新登录","kickout");
    }

    @RequestMapping(value = "/logout/fail")
    public Map<String,Object> logout(){
        return CommonUtils.getSucResultMap("退出成功");
    }

    @RequestMapping(value = "/login/time-out")
    public Map<String,Object> timeOut(){
        return CommonUtils.getResultMap("您登录超时，请重新登录","sessionTimeOut");
    }



}
