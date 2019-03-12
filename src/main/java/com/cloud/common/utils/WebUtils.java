package com.cloud.common.utils;

import javax.servlet.http.HttpServletRequest;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * web项目工具类
 * @author zengqh@sunment.com
 */
public class WebUtils {

	/**
	 * 获取request对象中所有参数，并设置到map中
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return 将request的请求参数封装成map（包括URL传和通过form表单提交的参数）
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getMapByRequest(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = enu.nextElement();
			String paraValue = request.getParameter(paraName).trim();
			map.put(paraName, paraValue);
		}

		if (checkObj(request.getAttribute("sUrl"))) {
			map.put("sURLs", request.getAttribute("sUrl").toString());
		}
		return map;
	}

	/**
	 * 获取request对象中所有参数，并设置到map中
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return 将request的请求参数封装成map（包括URL传和通过form表单提交的参数）
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getMapByRequestNotEmpty(
			HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = enu.nextElement();
			String paraValue = request.getParameter(paraName).trim();
			if (isEmptyWithNull(paraValue))
				map.put(paraName, paraValue);
		}

		if (checkObj(request.getAttribute("sUrl"))) {
			map.put("sURLs", request.getAttribute("sUrl").toString());
		}
		return map;
	}

	/**
	 * <PRE>
	 * 空判断，"null" 也为空
	 * 	    str = null;
	 * 		flag = WebUtils.isEmptyWithNull(str);
	 * 		Assert.assertEquals(true, flag);
	 * 		str = "";
	 * 		flag = WebUtils.isEmptyWithNull(str);
	 * 		Assert.assertEquals(true, flag);
	 * 		str = "null";
	 * 		flag = WebUtils.isEmptyWithNull(str);
	 * 		Assert.assertEquals(true, flag);
	 * 		str = "NULL";
	 * 		flag = WebUtils.isEmptyWithNull(str);
	 * 		Assert.assertEquals(true, flag);
	 * 		str = "nUll";
	 * 		flag = WebUtils.isEmptyWithNull(str);
	 * 		Assert.assertEquals(true, flag);
	 * 		str = " ";
	 * 		flag = WebUtils.isEmptyWithNull(str);
	 * 		Assert.assertEquals(false, flag);
	 * 		str = "    ";
	 * 		flag = WebUtils.isEmptyWithNull(str);
	 * 		Assert.assertEquals(false, flag);
	 * 		str = " a  ";
	 * 		flag = WebUtils.isEmptyWithNull(str);
	 * 		Assert.assertEquals(false, flag);
	 * </PRE>
	 * 
	 * @param cs
	 * @return
	 */
	public static boolean isEmptyWithNull(final CharSequence cs) {
		return cs == null || cs.length() == 0
				|| "null".equalsIgnoreCase(cs.toString());
	}

	/**
	 * <PRE>
	 * 非空判断，"null" 也为空
	 * 	 	str = null;
	 * 		flag = WebUtils.isBlankWithNull(str);
	 * 		Assert.assertEquals(true, flag);
	 * 		str = "";
	 * 		flag = WebUtils.isBlankWithNull(str);
	 * 		Assert.assertEquals(true, flag);
	 * 		str = "null";
	 * 		flag = WebUtils.isBlankWithNull(str);
	 * 		Assert.assertEquals(true, flag);
	 * 		str = "NULL";
	 * 		flag = WebUtils.isBlankWithNull(str);
	 * 		Assert.assertEquals(true, flag);
	 * 		str = "nUll";
	 * 		flag = WebUtils.isBlankWithNull(str);
	 * 		Assert.assertEquals(true, flag);
	 * 		str = " ";
	 * 		flag = WebUtils.isBlankWithNull(str);
	 * 		Assert.assertEquals(true, flag);
	 * 		str = "    ";
	 * 		flag = WebUtils.isBlankWithNull(str);
	 * 		Assert.assertEquals(true, flag);
	 * 		str = " a  ";
	 * 		flag = WebUtils.isBlankWithNull(str);
	 * 		Assert.assertEquals(false, flag);
	 * </PRE>
	 * 
	 * @param cs
	 * @return
	 */
	public static boolean notEmptyWithNull(final CharSequence cs) {
		return !isEmptyWithNull(cs);
	}

	/**
	 * <PRE>
	 * 空判断，"null" 也为空，空格也为空
	 * 	 str = null;
	 * 		flag = WebUtils.isBlankWithNull(str);
	 * 		Assert.assertEquals(true, flag);
	 * 		str = "";
	 * 		flag = WebUtils.isBlankWithNull(str);
	 * 		Assert.assertEquals(true, flag);
	 * 		str = "null";
	 * 		flag = WebUtils.isBlankWithNull(str);
	 * 		Assert.assertEquals(true, flag);
	 * 		str = "NULL";
	 * 		flag = WebUtils.isBlankWithNull(str);
	 * 		Assert.assertEquals(true, flag);
	 * 		str = "nUll";
	 * 		flag = WebUtils.isBlankWithNull(str);
	 * 		Assert.assertEquals(true, flag);
	 * 		str = " ";
	 * 		flag = WebUtils.isBlankWithNull(str);
	 * 		Assert.assertEquals(true, flag);
	 * 		str = "    ";
	 * 		flag = WebUtils.isBlankWithNull(str);
	 * 		Assert.assertEquals(true, flag);
	 * 		str = " a  ";
	 * 		flag = WebUtils.isBlankWithNull(str);
	 * 		Assert.assertEquals(false, flag);
	 * </PRE>
	 * 
	 * @param cs
	 * @return
	 */
	public static boolean isBlankWithNull(final CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0
				|| "null".equalsIgnoreCase(cs.toString())) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <PRE>
	 * 非空判断，"null" 也为空，空格也为空
	 * 	   str = null;
	 * 		flag = WebUtils.notBlankWithNull(str);
	 * 		Assert.assertEquals(false, flag);
	 * 		str = "";
	 * 		flag = WebUtils.notBlankWithNull(str);
	 * 		Assert.assertEquals(false, flag);
	 * 		str = "null";
	 * 		flag = WebUtils.notBlankWithNull(str);
	 * 		Assert.assertEquals(false, flag);
	 * 		str = "NULL";
	 * 		flag = WebUtils.notBlankWithNull(str);
	 * 		Assert.assertEquals(false, flag);
	 * 		str = "nUll";
	 * 		flag = WebUtils.notBlankWithNull(str);
	 * 		Assert.assertEquals(false, flag);
	 * 		str = " ";
	 * 		flag = WebUtils.notBlankWithNull(str);
	 * 		Assert.assertEquals(false, flag);
	 * 		str = "    ";
	 * 		flag = WebUtils.notBlankWithNull(str);
	 * 		Assert.assertEquals(false, flag);
	 * 		str = " a  ";
	 * 		flag = WebUtils.notBlankWithNull(str);
	 * 		Assert.assertEquals(true, flag);
	 * </PRE>
	 * 
	 * @param cs
	 * @return
	 */
	public static boolean notBlankWithNull(final CharSequence cs) {
		return !isBlankWithNull(cs);
	}

	/**
	 * 判断对象不为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean checkObj(Object obj) {
		boolean bool = true;
		if (obj == null || "".equals(obj.toString().trim()))
			bool = false;
		return bool;
	}

	/**
	 * request取Int值
	 * 
	 * @param key
	 *            属性名称
	 * @param request
	 *            HttpServletRequest
	 * @return
	 */
	public static String getString(String key, HttpServletRequest request) {
		if (null == request.getParameter(key)) return null;
		return String.valueOf(request.getParameter(key));
	}

	/**
	 * request取Int值
	 * 
	 * @param key
	 *            属性名称
	 * @param request
	 *            HttpServletRequest
	 * @return
	 */
	public static Integer getInt(String key, HttpServletRequest request) {
		if (null == request.getParameter(key)) return null;
		return Integer.parseInt(request.getParameter(key));
	}

	/**
	 * request取Long值
	 * 
	 * @param key
	 *            属性名称
	 * @param request
	 *            HttpServletRequest
	 * @return
	 */
	public static Long getLong(String key, HttpServletRequest request) {
		if (null == request.getParameter(key)) return null;
		return Long.parseLong(request.getParameter(key));
	}

	/**
	 * request取Boolean值
	 * 
	 * @param key
	 *            属性名称
	 * @param request
	 *            HttpServletRequest
	 * @return
	 */
	public static Boolean getBoolean(String key, HttpServletRequest request) {
		String tmp = request.getParameter(key);
		Boolean flag = true;
		if ("true".equalsIgnoreCase(tmp) || "false".equalsIgnoreCase(tmp)) {
			if ("true".equalsIgnoreCase(tmp)) {
				flag = true;
			} else { // 非true全部返回false
				flag = false;
			}
		} else {
			if ("1".equals(tmp)) {
				flag = true;
			} else { // 非1则全部返回false
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * request取Short值
	 * 
	 * @param key
	 *            属性名称
	 * @param request
	 *            HttpServletRequest
	 * @return
	 */
	public static Short getShort(String key, HttpServletRequest request) {
		if (null == request.getParameter(key)) return null;
		return Short.parseShort(request.getParameter(key));
	}

	/**
	 * request取Int值
	 * 
	 * @param obj
	 * @return
	 */
	public static String getString(Object obj) {
		if (null == obj) return null;
		return String.valueOf(obj);
	}

	/**
	 * request取Int值
	 * 
	 * @param obj
	 * @return
	 */
	public static Integer getInt(Object obj) {
		if (null == obj) return null;
		return Integer.parseInt(obj.toString());
	}

	/**
	 * request取Long值
	 * 
	 * @param obj
	 * @return
	 */
	public static Long getLong(Object obj) {
		if (null == obj||"".equals(obj)) return null;
		return Long.parseLong(obj.toString());
	}

	/**
	 * request取Boolean值
	 * 
	 * @param obj
	 * @return
	 */
	public static Boolean getBoolean(Object obj) {
		if (null == obj) return null;
		String tmp = obj.toString();
		Boolean flag = true;
		if ("true".equalsIgnoreCase(tmp) || "false".equalsIgnoreCase(tmp)) {
			if ("true".equalsIgnoreCase(tmp)) {
				flag = true;
			} else { // 非true全部返回false
				flag = false;
			}
		} else {
			if ("1".equals(tmp)) {
				flag = true;
			} else { // 非1则全部返回false
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * request取Short值
	 * 
	 * @param obj
	 * @return
	 */
	public static Short getShort(Object obj) {
		if (null == obj) return null;
		return Short.parseShort(obj.toString());
	}

	/**
	 * request取Int值
	 * 
	 * @param key
	 *            属性名称
	 * @param map
	 *            集合
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getString(String key, Map map) {
		if (map.containsKey(key) && null != map.get(key)) {
			return String.valueOf(map.get(key));
		}  else {
			return null;
		}
	}

	/**
	 * request取Int值
	 * 
	 * @param key
	 *            属性名称
	 * @param map
	 *            集合
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Integer getInt(String key, Map map) {
		if (map.containsKey(key) && null != map.get(key)) {
			return Integer.parseInt(map.get(key).toString());
		}  else {
			return null;
		}
	}

	/**
	 * request取Long值
	 * 
	 * @param key
	 *            属性名称
	 * @param map
	 *            集合
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Long getLong(String key, Map map) {
		if (map.containsKey(key) && null != map.get(key)) {
			return Long.parseLong(map.get(key).toString());
		}  else {
			return null;
		}
	}

	/**
	 * request取Boolean值
	 * 
	 * @param key
	 *            属性名称
	 * @param map
	 *            集合
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Boolean getBoolean(String key, Map map) {
		if (!map.containsKey(key) || null == map.get(key)) {
			return null;
		}
		String tmp = map.get(key).toString();
		Boolean flag = true;
		if ("true".equalsIgnoreCase(tmp) || "false".equalsIgnoreCase(tmp)) {
			if ("true".equalsIgnoreCase(tmp)) {
				flag = true;
			} else { // 非true全部返回false
				flag = false;
			}
		} else {
			if ("1".equals(tmp)) {
				flag = true;
			} else { // 非1则全部返回false
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * request取Short值
	 * 
	 * @param key
	 *            属性名称
	 * @param map
	 *            集合
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Short getShort(String key, Map map) {
		if (map.containsKey(key) && null != map.get(key)) {
			return Short.parseShort(map.get(key).toString());
		}  else {
			return null;
		}
	}

}
