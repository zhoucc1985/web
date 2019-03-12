package com.cloud.common.utils.cloud;

import com.cloud.common.dict.cloud.CommonDict;
import com.cloud.common.dict.cloud.UserRole;
import com.cloud.common.utils.CommonUtils;
import com.cloud.common.utils.Digests;
import com.cloud.common.utils.ObjectUtils;
import com.cloud.shiro.cloud.ShiroUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;

import java.io.Serializable;

/**
 * 用户工具类
 * @author zhoucc
 */
public class UserUtils {

	/**
	 * 获得当前登录的shiroUser
	 * @return
	 */
	public static ShiroUser getShiroUser() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if (ObjectUtils.isEmptyObject(user)){
			user = new ShiroUser(1L, "admin1",
					"admin1",1L,"ADMIN",19L,"");
		}
		return user;
	}

	/**
	 * 获得当前用户登录名，如果抛错就返回“system”
	 * @return
	 */
	public static String getShiroUserLoginNameWithDefault() {
		try {
			return getShiroUser().loginName;
		} catch (Exception e) {
			return "system";
		}

	}

	/**
	 * 获得当前用户id，如果抛错就返回 null
	 * @return
	 */
	public static Long getShiroUserIdWithDefault() {
		try {
			return getShiroUser().id;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 获得当前登录人的ID
	 * @return
	 */
	public static Long getCurrentUserId() {
		ShiroUser shiroUser = getShiroUser();
		return shiroUser.id;
	}

	/**
	 * 查询当前登录人角色ID
	 * @return
	 */
	public static Long getCurrentUserRoleId() {
		return getShiroUser().getRoleId();
	}

	/**
	 * 获取当前登陆人名称
	 * @auth shrChang.Liu
	 * @date 2017年12月15日下午3:27:36
	 * @return
	 */
	public static String getCurrentUserName(){
		ShiroUser shiroUser = getShiroUser();
		return shiroUser.name;
	}


	public static Serializable getShiroSessionId() {
		return  SecurityUtils.getSubject().getSession().getId();
	}

	public static byte[] getGenerateSalt() {
		byte[] salt = Digests.generateSalt(CommonDict.SALT_SIZE);
		return salt;
	}

	public static String getGenerateSaltStr() {
		byte[] salt = getGenerateSalt();
		return CommonUtils.getHexEncode(salt);
	}

	public static String getUserSHA1Password(String plainPassword) {
		return getUserSHA1Password(plainPassword, getGenerateSaltStr());
	}

	public static String getUserSHA1Password(String plainPassword, String salt) {
		byte[] pwd =  Digests.sha1(plainPassword.getBytes(), CommonUtils.getHexDecode(salt), CommonDict.HASH_INTERATIONS);
		return CommonUtils.getHexEncode(pwd);
	}

	/**
	 * 判断是否是学生
	 * @auth shrChang.Liu
	 * @date 2018年1月5日下午4:55:24
	 * @param user
	 * @return
	 */
	public static boolean isStudent(ShiroUser user){
		return StringUtils.equals(user.roleCode,UserRole.CLOUD_STUDENT_ROLE);
	}

	/**
	 * 判断是否是学生
	 * @return 返回结果
	 */
	public static boolean isStudent(){
		return isStudent(getShiroUser());
	}

	/**
	 * 判断是否是老师
	 * @auth shrChang.Liu
	 * @date 2018年1月8日下午6:02:30
	 * @param user
	 * @return
	 */
	public static boolean isTeacher(ShiroUser user){
		return  StringUtils.equals(user.roleCode,UserRole.CLOUD_TEACHER_ROLE);
	}

	/**
	 *  判断是否是老师
	 * @return 返回结果
	 */
	public static boolean isTeacher(){
		return isTeacher(getShiroUser());
	}


	/**
	 * 判断是否为系统管理员
	 * @param user 用户
	 * @return   返回结果
	 */
	public static boolean isAdmin(ShiroUser user) {
		return StringUtils.equals(user.roleCode,UserRole.CLOUD_ADMIN_ROLE);
	}

	/**
	 * 判断是否为系统管理员
	 * @return 返回结果
	 */
	public static boolean isAdmin() {
		return isAdmin(getShiroUser());
	}

	/**
	 * 判断当前用户是不是系统管理员
	 * @return 返回结果 true 为非管理员，false为系统管理员
	 */
	public static  boolean isNoAdmin() {
		return !isAdmin();
	}
}
