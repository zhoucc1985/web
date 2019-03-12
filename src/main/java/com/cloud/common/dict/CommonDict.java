package com.cloud.common.dict;

/**
 * 公共常量字典
 *
 * @author Pan Jianneng
 */
public class CommonDict {

	public static final String RETURN_STATE_SUC = "suc";
	public static final String RETURN_STATE_SUC_MSG = "执行成功";
	public static final String RETURN_STATE_FAIL = "fail";
	public static final String RETURN_STATE_FAIL_MSG = "执行失败";
	public static final String RETURN_STATE_WARN = "warn";
	public static final String RETURN_STATE_WAEN_MSG = "警告";

	/**
	 * ************************** 组织机构相关常量 *******************************
	 */
	/**
	 * 教育局类型
	 */
	public static final String ORG_TYPE_EDUCATION = "EDUCATION";
	/**
	 * 省厅类型
	 */
	public static final String ORG_TYPE_PROVINCE = "PROVINCE";
	/**
	 * 学校类型
	 */
	public static final String ORG_TYPE_SCHOOL = "SCHOOL";
	/**
	 * 学校部门类型
	 */
	public static final String ORG_TYPE_DEPARTMENT = "DEPARTMENT";

	/**
	 * AuthenticationInfo 加密用的saltSize
	 */
	public final static int SALT_SIZE = 8;

	/**
	 * 用户 密码 校验 salt 和计算次数
	 */
	public static final String HASH_ALGORITHM = "MD5";
	public static final int HASH_ITERATIONS = 5;

	/**
	 * 默认分页数
	 */
	public final static int DEFAULT_PAGE_SIZE = 30;
	
	public static final String SUPER_ADMIN_LOGINNAME="admin";

}
