package com.cloud.collectRule;


public interface Validater {
	
	/** 数据正确 */
	public static final int RIGHT = 1;
	
	/** 导入的数据没有对应的枚举值，无法翻译字段 */
	public static final int ERR_EMUE = 2;
	
	/** 数据库中对应的表无此数据，无法翻译字段 */
	public static final int ERR_FOREIGN = 3;
	
	/** 日期格式错误 */
	public static final int ERR_DATE = 4;
	
	/** 无效数字,或数字精度不足 */
	public static final int ERR_NUMBER = 5;
	
	/** 字符串长度过长 */
	public static final int ERR_LENGTH = 6;
	
	/** 待验证父节点 */
	public static final int DELAY_PARENT = 7;
	
	/** 未知错误 */
	public int ERR_OTHER = 99;
	
	public int validateErr(String value);
	
	public String getValidateValue(String value);
}
