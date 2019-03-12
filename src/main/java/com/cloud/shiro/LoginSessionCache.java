package com.cloud.shiro;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 超时session缓存
 * @ClassName LoginSessionCache.java
 * @author shrChang.Liu
 * @date 2018年1月12日上午9:52:15
 */
public class LoginSessionCache {

	private static Map<Serializable,String> loginSessionMap = new ConcurrentHashMap<Serializable, String>();



	/**
	 * 登录超时时，添加到超时缓存中
	 * @param sessionId
	 */
	public static void addSession(Serializable sessionId,String sessionState){
		loginSessionMap.put(sessionId, sessionState);
	}

	/**
	 * session失效需要把从缓存中清除
	 * @param sessionId
	 */
	public static void removeSession(Serializable sessionId) {
		loginSessionMap.remove(sessionId);
	}

	/**
	 * 校验当前session是否超时
	 * @param sessionId
	 */
	public static boolean isExpiration(Serializable sessionId){
		return BasicControlFilterTool.SESSION_EXPIRATION.equals(loginSessionMap.get(sessionId));
	}
}
