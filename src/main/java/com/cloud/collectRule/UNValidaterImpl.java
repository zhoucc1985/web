package com.cloud.collectRule;


import java.util.Map;


public class UNValidaterImpl implements Validater {
	
	/**
	 * 验证字段的数据类型。
	 * 1：日期， 2：字符串， 3：数字
	 */
	private int CHANGE_TYPE;
	// Dialect dia = DialectFactory.getDialect();
	/** 日期及数字类型的正则式 */
	private String regex ="-?[0-9]+.?[0-9]+";
	
	private int fieldLength	=0;
	
	public String UNValidaterImpl(Map<String, String> conf) {
		String dataType = conf.get("fieldType");
		if("datetime".equalsIgnoreCase(dataType) || "date".equalsIgnoreCase(dataType)) {
			CHANGE_TYPE = 1;
			regex = "^((\\d{2}(([02468][048])|([13579][26]))"
					+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
					+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
					+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
					+ "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
					+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((1|0?)[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]))?";
		} else if("varchar".equalsIgnoreCase(dataType) || "char".equalsIgnoreCase(dataType)){
			CHANGE_TYPE = 2;
			fieldLength = StringUtil.toInt(conf.get("fieldLength"));
		} else if("decimal".equalsIgnoreCase(dataType) || "int".equalsIgnoreCase(dataType) || "bigint".equalsIgnoreCase(dataType)) {
			CHANGE_TYPE = 3;
			String fieldScale = conf.get("fieldScale");
			String fieldPrec = conf.get("fieldPrec");
			fieldPrec = StringUtil.toInt(fieldPrec) - StringUtil.toInt(fieldScale)+"";
			
			regex = "-?[0-9]{0,"+fieldPrec+"}.?[0-9]{0,"+fieldScale+"}";
		}else {
			CHANGE_TYPE = 0;
		}
		return null;
		
	}
	
	public int validateErr(String value) {
		
		if(value == null || "".equals(value)) {
			return RIGHT;
		} else if(CHANGE_TYPE == 2){
			try{
				int length = value.getBytes("utf-8").length;
				if(length > fieldLength) {
					return ERR_LENGTH;
				} else {
					return RIGHT;
				}
			} catch(Exception e) {
				return ERR_OTHER;
			}
		} else {
			if(CHANGE_TYPE == 1) { //如果日期只有年份时, 则补全到年月日,不然验证不了日期
				value = getDateString(value);
			}
			boolean flag = StringUtil.checkRegexp(value, this.regex);
			if(!flag){
				if(CHANGE_TYPE == 1) {
					return ERR_DATE;
				} else if(CHANGE_TYPE == 3) {
					return ERR_NUMBER; 
				} else {
					return ERR_OTHER;
				}
			} else {
				return RIGHT;
			}
		}
	}

	@Override
	public String getValidateValue(String value) {
		if(value == null || "null".equals(value) || "".equals(value)) {
			return null;
		}
		if(CHANGE_TYPE == 1) {
			//return dia.stringToDatetime(SqlUtil.getDateString(value));
		} if(CHANGE_TYPE == 2) {
			return "'" + value + "'";
		}else {
			return value;
		}
	}
	
	/**
	 * 补全日期长度
	 * @param str
	 * @return
	 */
	public static String getDateString(String str) {
		if (str.length() == 4) {
			str += "-01-01 00:00:00";
		}else if (str.length() == 7) {
			str += "-01 00:00:00";
		}else if (str.length() == 10) {
			str += " 00:00:00";
		}else if (str.length() == 13) {
			str += ":00:00";
		}else if (str.length() == 16) {
			str += ":00";
		}
		return str;
	}
	

}
