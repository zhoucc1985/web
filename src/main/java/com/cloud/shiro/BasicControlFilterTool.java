package com.cloud.shiro;

import com.cloud.common.utils.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 不做重定向，直接给出返回消息
 *
 * @author junli
 */
public class BasicControlFilterTool {

	/**
	 * 日志
	 */
	private static final Logger log = LoggerFactory
			.getLogger(BasicControlFilterTool.class);

	public static final String SESSION_EXPIRATION = "expiration";
	public static String SESSION_SID = "cloudIASSession";

	public static void issueForward(ServletRequest request, ServletResponse response,
	                                String controllerUrl) throws Exception {
		issueForward(request, response, controllerUrl, null);
	}

	public static void issueForward(ServletRequest request, ServletResponse response, String controllerUrl,
	                                Map<String, Object> attributesMap) throws Exception {
		WebUtils.saveRequest(request);
		HttpServletRequest httpRequest = WebUtils.toHttp(request);
		if (attributesMap != null) {
			for (String key : attributesMap.keySet()) {
				httpRequest.setAttribute(key, attributesMap.get(key));
			}
		}


		httpRequest.getRequestDispatcher(controllerUrl).forward(request, response);
	}

	/**
	 * 调整代码实现方式，采用jdk1.8
	 * @author lq
	 * @param request  request请求
	 * @return  cookie 对应value
	 */
	public static String getCookieSessionId(ServletRequest request) {
		HttpServletRequest httpRequest = WebUtils.toHttp(request);
		Cookie[] cookies = httpRequest.getCookies();
		if (ObjectUtils.isEmptyArray(cookies)) {
			return "";
		}
		String matchCookieValue = Arrays.stream(cookies)
				.filter(cookie -> StringUtils.equals(SESSION_SID, cookie.getName()))
				.map(Cookie::getValue)
				.findFirst()
				.orElse("");

		return matchCookieValue;
	}

	public static Map<String, Object> getExpirationRedirectMap() {
		return getRedirectMap(SESSION_EXPIRATION);
	}

	private static Map<String, Object> getRedirectMap(String sessionState) {
		Map<String, Object> map = new HashMap<String, Object>(0);
		map.put(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, sessionState);
		return map;
	}
}
