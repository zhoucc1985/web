package com.cloud.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 对象工具
 * @author Pan Jianneng
 */
public class ObjectUtils {

	public static boolean isEmptyObject(Object o) {
		return null == o;
	}

	public static boolean isNotEmptyObject(Object o) {
		return !isEmptyObject(o);
	}

	public static boolean isEmptyList(List list) {
		return null == list || list.isEmpty();
	}

	public static boolean isEmptyCollection(Collection list) {
		return null == list || list.isEmpty();
	}

	public static boolean isEmptyMap(Map map) {
		return null == map || map.isEmpty();
	}

	public static boolean isNotEmptyMap(Map map) {
		return !isEmptyMap(map);
	}

	public static boolean isNotEmptyList(List list) {
		return !isEmptyList(list);
	}

	public static boolean isEmptyString(String s) {
		return null == s || "".equals(s);
	}

	public static boolean isNotEmptyString(String s) {
		return !isEmptyString(s);
	}

	public static boolean isEmptyArray(Object[] objs) {
		return (null == objs || objs.length == 0);
	}

	public static boolean isNotEmptyArray(Object[] objs) {
		return !isEmptyArray(objs);
	}

	public static boolean isNullObject(Object o) {
		return null == o;
	}

	public static boolean isNotNullObject(Object o) {
		return !isNullObject(o);
	}

	public static boolean isHaveField(String fieldName, Object o) {

		if (isEmptyString(fieldName) || isEmptyObject(o)) {
			return false;
		}

		if (o instanceof Map) {
			Map<String, Object> map = (Map) o;
			return map.containsKey(fieldName);
		}

		try {
			Class classTmp = null;
			if (o instanceof Class) {
				classTmp = (Class) o;
			} else {
				classTmp = o.getClass();
			}

			Field[] fields = classTmp.getDeclaredFields();
			if (isEmptyArray(fields)) {
				return false;
			}
			boolean result = false;
			for (Field field : fields) {
				if (field.getName().equals(fieldName)) {
					result = true;
					break;
				}
			}

			return result;
		} catch (Exception e) {
			//// logger.error(e.getMessage()+"没找到object属性。");
			return false;
		}
	}

	public static boolean isNotHaveField(String fieldName, Object o) {
		return !isHaveField(fieldName, o);
	}

	/**
	 * 无日期类型数据转换
	 * @param value
	 * @param objClass
	 * @return
	 * @throws RuntimeException
	 */
	public static <T> T getObjectByJsonStr(String value,Class<T> objClass) throws RuntimeException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(value, objClass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("JSON 格式错误");
		}
	}

	public static String getJsonStrByObject(Object obj)throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

	/**
	 * 去除字符串首尾出现的某个字符.
	 * @param source 源字符串.
	 * @param element 需要去除的字符.
	 * @return String.
	 */
     public static String trimFirstAndLastChar(String source,char element) throws Exception{
     	boolean beginIndexFlag = true;
     	boolean endIndexFlag = true;
     	do{
     		int beginIndex = source.indexOf(element) == 0 ? 1 : 0;
     		int endIndex = source.lastIndexOf(element) + 1 == source.length() ? source.lastIndexOf(element) : source.length();
     		if(endIndex!=-1){
				source = source.substring(beginIndex, endIndex);
			}
     		beginIndexFlag = (source.indexOf(element) == 0);
     		endIndexFlag = (source.lastIndexOf(element) + 1 == source.length());
     	} while (beginIndexFlag || endIndexFlag);
     	return source;
     }
}
