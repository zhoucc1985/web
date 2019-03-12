package com.cloud.common.dict.cloud;

/**
 * 公共常量字典
 * @author zhoucc
 */
public class CommonDict {
	public final static int SALT_SIZE = 8;

	/**
	 * 用户 密码 校验 salt 和计算次数
	 */
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1536;

	/**
	 * 默认密码
	 */
	public static final String DEFAULT_PASSWORD = "123456";

	public static final String RETURN_STATE_SUC  	= "suc";
	public static final String RETURN_STATE_SUC_MSG = "执行成功";
	public static final String RETURN_STATE_FAIL = "fail";
	public static final String RETURN_STATE_FAIL_MSG = "执行失败";

	public static final String STATE = "state";

	/**
	 * 默认分页数
	 */
	public final static int DEFAULT_PAGE_SIZE = 30;

	/**
	 * ueditor上传存储根路径
	 */
	public final static String UPLOAD_BASE_URI = "/ueditor";

	
}
