package com.cloud.common.utils;

import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.cloud.common.dict.CommonDict;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 公共工具类
 * @author Pan Jianneng
 */
public class CommonUtils {

	protected static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

	public static String getHexEncode(byte[] values) {
		return Encodes.encodeHex(values);
	}

	public static byte[] getHexDecode(String value) {
		return Encodes.decodeHex(value);
	}

	/**
	 * 把 基于 SHA 加密方式的 byte 转换成 16进制的字符串
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public  static String getSHAHexStr(byte[] data)throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA");
		byte[] digest = md.digest(data);
		return new HexBinaryAdapter().marshal(digest);
	}

	public static String getRemoteHost(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
	}


	/**
	 *
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String getJsonStrByObject(Object obj)throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

	/**
	 * 字符串转换为Integer
	 * @param value
	 * @return
	 */
	public static Integer covertString2Integer(String value) {
		return covertString2IntegerWithDefault(value, null);
	}

	/**
	 * 字符串转换为Integer
	 * @param value
	 * @return
	 */
	public static Integer covertString2IntegerWithDefault(String value, Integer defaultValue) {
		return StringUtils.isEmpty(value) ? defaultValue : Integer.parseInt(value);
	}



	/**
	 * Controller返回 错误结果
	 * @return
	 */
	public static Map<String, Object> getErrorResultMap() {
		return getResultMap(CommonDict.RETURN_STATE_FAIL_MSG,
				CommonDict.RETURN_STATE_FAIL);
	}

	/**
	 * Controller返回 错误结果
	 * @param msg 错误信息
	 * @return
	 */
	public static Map<String, Object> getErrorResultMap(String msg) {
		if(StringUtils.isEmpty(msg)) {
			return getErrorResultMap();
		}
		return getResultMap(msg, CommonDict.RETURN_STATE_FAIL);
	}

	/**
	 * Controller返回 警告结果 必须添加消息
	 * @param msg 警告信息
	 * @return
	 */
	public static Map<String, Object> getWarnResultMap(String msg) {
		return getResultMap(msg, CommonDict.RETURN_STATE_WARN);
	}

	/**
	 * Controller返回 正确结果
	 * @return
	 */
	public static Map<String, Object> getSucResultMap() {
		return getResultMap(CommonDict.RETURN_STATE_SUC_MSG,
				CommonDict.RETURN_STATE_SUC);
	}

	/**
	 * Controller返回 正确结果
	 * @param msg 正确信息
	 * @return
	 */
	public static Map<String, Object> getSucResultMap(String msg) {
		return getResultMap(msg, CommonDict.RETURN_STATE_SUC);
	}

	/**
	 * Controller返回 结果 组装
	 * @param msg    结果信息
	 * @param state  结果状态   "suc"：执行成功；"fail"：执行失败;"warn":警告
	 * @return
	 */
	public static Map<String,Object> getResultMap(String msg, String state) {
		Map<String,Object> resultMap = new HashMap<String, Object>(2);
		resultMap.put("msg", msg);
		resultMap.put("state", state);
		return resultMap;
	}

	/**
	 *  Controller返回 结果 组装
	 * @param data
	 * @param total
	 * @return
	 */
	public static Map<String,Object> getSucResultMap(Object data, Long total) {
		Map<String,Object> resultMap = new HashMap<String, Object>(4);
		resultMap.put("msg", CommonDict.RETURN_STATE_SUC_MSG);
		resultMap.put("state", CommonDict.RETURN_STATE_SUC);
		resultMap.put("datas", data);
		resultMap.put("total", total);
		return resultMap;
	}

	/**
	 *  Controller返回 结果 组装
	 * @param data
	 * @param total
	 * @return
	 */
	public static Map<String,Object> getSucResultMap(Object data, Integer total) {
		return getSucResultMap(data, Long.valueOf(total));
	}

	/**
	 *  Controller返回 结果 组装
	 * @param data
	 * @return
	 */
	public static Map<String,Object> getSucResultMap(Object data) {
		return getSucResultMap(data,1);
	}


	/**
	 * 去掉文件拓展名
	 * @param filename
	 * @return
	 */
	public static String getFileNameNoEx(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}

	public static <E,T> Map<E, T> colToMap(Collection<T> col, Class<T> entityClass, Class<E> keyClass, String propertyName) {
		Map<E,T> map = new LinkedHashMap<>();
		if (CollectionUtils.isEmpty(col)) {
			return map;
		}
		for (T i : col) {
			E key;
			try {
				key = (E)new PropertyDescriptor(propertyName, entityClass).getReadMethod().invoke(i);
				map.put(key , i);
			} catch (Throwable e) {

			}

        }
        return map;
	}

	public static <E,T> List<E> collect(Collection<T> col, Class<T> entityClass, Class<E> keyClass, String propertyName) {
		List<E> list = new ArrayList();
		if (CollectionUtils.isEmpty(col)) {
			return list;
		}
		for (T t: col) {
			E value;
			try {
				value = (E)new PropertyDescriptor(propertyName, entityClass).getReadMethod().invoke(t);
				list.add(value);
			} catch (Throwable e) {

			}
		}
		return list;
	}

	/**
	 * 把一定规则的ID集合字符串 转换为List集合
	 * @param idStrs   ID集合字符串
	 * @param split   如果是特殊字符请加上转义符
	 * @return
	 */
	public static List<Long> conver2IdsList(String idStrs, String split) {
		if(StringUtils.isEmpty(idStrs)) {
			return new ArrayList<Long>();
		}
		List<Long> ids = new ArrayList<Long>();
		if(idStrs.indexOf(split) != -1) {
			String[] idArray = idStrs.split(split);
			for(String idStr : idArray) {
				if(StringUtils.isNotEmpty(idStr)) {
					ids.add(Long.parseLong(idStr));
				}
			}
		}else {
			ids.add(Long.parseLong(idStrs));
		}
		return ids;

	}


	/**
	 * 获得本地的Mac地址
	 * @return
	 */
	public static String getLocalMACAddress() {
		String macString = getMACAddress("127.0.0.1");
		return macString.replace("-", "");
	}
	
	/**
	 * 通过IP地址获取MAC地址
	 * 
	 * @param ip
	 *            String,127.0.0.1格式
	 * @return mac String
	 * @throws Exception
	 */
	public static String getMACAddress(String ip) {
		String line = "";
		String macAddress = "";
		String macAddressPrefix = "MAC Address = ";
		String loopBackAddress = "127.0.0.1";
		
		// 获取非本地IP的MAC地址
		try {
			
			// 如果为127.0.0.1,则获取本地MAC地址。
			if (loopBackAddress.equals(ip)) {
				if (isWindowsOs()) {
					InetAddress inetAddress = InetAddress.getLocalHost();
					// 貌似此方法需要JDK1.6。
					byte[] mac = NetworkInterface.getByInetAddress(inetAddress)
							.getHardwareAddress();
					// 下面代码是把mac地址拼装成String
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < mac.length; i++) {
						if (i != 0) {
							sb.append("-");
						}
						// mac[i] & 0xFF 是为了把byte转化为正整数
						String s = Integer.toHexString(mac[i] & 0xFF);
						sb.append(s.length() == 1 ? 0 + s : s);
					}
					// 把字符串所有小写字母改为大写成为正规的mac地址并返回
					macAddress = sb.toString().trim().toUpperCase();
					return macAddress;
				} else {
					return getLinuxMacAddress();
				}
			}
			
			Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
			InputStreamReader isr = new InputStreamReader(p.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			while ((line = br.readLine()) != null) {
				if (line != null) {
					int index = line.indexOf(loopBackAddress);
					if (index != -1) {
						macAddress = line
								.substring(index + macAddressPrefix.length())
								.trim().toUpperCase();
					}
				}
			}
			br.close();
		} catch (IOException e) {
			logger.error("获取mac异常：", e);
		} catch (Exception e) {
			logger.error("获取mac异常：", e);
		}
		return macAddress;
	}
	/**
	 * 判断部署云平台的机子是否为windows系统，如果是为true，其余情况为false。
	 * @return
	 */
	public static boolean isWindowsOs() {
		boolean isWindows = false;
		String osName = System.getProperty("os.name");

		if (ObjectUtils.isEmptyString(osName) &&
				osName.toLowerCase().indexOf("windows") > -1) {
			isWindows = true;
		}

		return isWindows;
	}


	/**
	 * linux getLinuxMacAddress
	 * @return
	 * @throws Exception
	 */
	public static String getLinuxMacAddress() throws Exception {
        Enumeration<NetworkInterface> ni = NetworkInterface.getNetworkInterfaces();
          
        while(ni.hasMoreElements()){  
            NetworkInterface netI = ni.nextElement();
              
            byte[] bytes = netI.getHardwareAddress();  
            if(netI.isUp() && netI != null && bytes != null && bytes.length == 6){  
                StringBuffer sb = new StringBuffer();
                for(byte b:bytes){  
                     //与11110000作按位与运算以便读取当前字节高4位  
                     sb.append(Integer.toHexString((b&240)>>4));
                     //与00001111作按位与运算以便读取当前字节低4位  
                     sb.append(Integer.toHexString(b&15));
                     sb.append("-");  
                 }  
                 sb.deleteCharAt(sb.length()-1);  
                 return sb.toString().toUpperCase();   
            }  
        }  
        return null;  
    }

	/**
	 * 无日期类型数据转换
	 * @param value
	 * @param objClass
	 * @return
	 */
	public static <T> T getObjectByJsonStr(String value,Class<T> objClass) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(value, objClass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("JSON 格式错误");
		}
	}

	/**
	 * 带日期类型数据转换
	 * @param value
	 * @param objClass
	 * @param dateFormateStr  日期格式类型 “yyyy-MM-dd HH:mm:ss”
	 * @return
	 * @throws Exception
	 */
	public static <T> T getObjectByJsonStr(String value,Class<T> objClass,String dateFormateStr) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			DateFormat dateFormat = new SimpleDateFormat(dateFormateStr);
			mapper.setDateFormat(dateFormat);
			return mapper.readValue(value, objClass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("JSON 格式错误");
		}
	}

	/**
	 * 把 json 集合 转换为  特定Class 的集合
	 * @param value
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> getListByJsonStr(String value, Class<T> clazz) throws RuntimeException {
		return getListByJsonStr(value,clazz,null);
	}

	/**
	 * 把 json 集合 转换为  特定Class 的集合
	 * @param value
	 * @param clazz
	 * @param dateFormateStr  日期格式类型 “yyyy-MM-dd HH:mm:ss”
	 * @return
	 */
	public static <T> List<T> getListByJsonStr(String value, Class<T> clazz,String dateFormateStr) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		if(ObjectUtils.isNotNullObject(dateFormateStr)){
			DateFormat dateFormat = new SimpleDateFormat(dateFormateStr);
			mapper.setDateFormat(dateFormat);
		}
		mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz);
		try {
			return mapper.readValue(value, javaType);
		} catch (Exception e) {
			throw new RuntimeException("JSON 格式错误");
		}
	}
}
