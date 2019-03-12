package com.cloud.shiro;

import com.cloud.common.utils.ObjectUtils;
import com.cloud.common.vo.ShiroUser;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


/**
 * 用户登录缓存
 *
 * @author shrChang.Liu
 * @ClassName LoginShiroCache.java
 * @date 2018年1月11日下午5:17:36
 */
public class LoginShiroCache {

	private static Map<Serializable, ShiroUser> loginUserMap = new ConcurrentHashMap<Serializable, ShiroUser>();

	/**
	 * 添加在线用户
	 *
	 * @param sesstionId
	 * @param userInfo
	 */
	public static void addShiroUser(Serializable sesstionId, ShiroUser userInfo) {
		loginUserMap.put(sesstionId, userInfo);
	}

	/**
	 * 删除在线用户
	 *
	 * @param sesstionId
	 */
	public static void removeShiroUser(Serializable sesstionId) {
		if (loginUserMap.containsKey(sesstionId)) {
			loginUserMap.remove(sesstionId);
		}
	}

	/**
	 * 获取在线用户数
	 *
	 * @return
	 */
	public static Long getLoginNum() {
		return Long.valueOf(loginUserMap.size());
	}

	/**
	 * 通过sesstionId 获取用户
	 *
	 * @param sesstionId
	 * @return
	 */
	public static ShiroUser getShiroUserBySessionId(Serializable sesstionId) {
		return loginUserMap.get(sesstionId);
	}

	/**
	 * 获取所有在线用户信息
	 *
	 * @return
	 */
	public static List<ShiroUser> getLoginUserList() {
		return new ArrayList<>(loginUserMap.values());
	}

	/**
	 * 返回所有登录用户的map key 为登录用户ShiroUser的id value 为ShiroUser对象
	 *
	 * @return
	 */
	public static Map<Long, ShiroUser> getLoginUserMap() {
		List<ShiroUser> shiroUsers = getLoginUserList();
		Map<Long, ShiroUser> shiroUserMap = shiroUsers
				.stream()
				.collect(Collectors.toMap(ShiroUser::getId,(shiroUser -> shiroUser)));
		return shiroUserMap;
	}

	public static boolean isUserLoginContains(String loginName) {
		List<Serializable> idList = getUserSerializable(loginName);
		return ObjectUtils.isNotEmptyList(idList);
	}

	public static List<Serializable> getUserSerializable(String loginName) {
		List<Serializable> idList = loginUserMap.keySet()
				.stream()
				.filter(id -> StringUtils.equals(loginName, loginUserMap.get(id).loginName))
				.collect(Collectors.toList());
		return idList;
	}
}
