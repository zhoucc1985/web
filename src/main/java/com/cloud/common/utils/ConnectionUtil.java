package com.cloud.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionUtil {

    /**
     * 获取连接
     * @param dbType 数据库类型 oracle mysql sqlServer
     * @param ip ip地址
     * @param port 端口
     * @param user  用户名
     * @param password 密码
     * @param dbName 数据库名
     * @param jdbcUrl 连接字符串
     * @param version sqlserver版本  sqlserver 2000 sqlserver 2005以上
     * @param oracleType oracle类型  1服务名  2 sid
     * @param sid sid名称
     * @param serviceName 服务名
     * @return 连接对象Connection
     */
	public static Connection getConnection(String dbType, String ip, int port, String user, String password,
			String dbName, String jdbcUrl, String version,Integer oracleType,String sid,String serviceName) {
		Connection conn = null;
		try {
			if ("oracle".equals(dbType)) {
				Properties props = new Properties();
				props.put("user", user);
				props.put("password", password);
				props.put("remarksReporting", "true");
				conn = DriverManager.getConnection(getUrl(dbType, ip, port, dbName, jdbcUrl, version,oracleType,sid,serviceName), props);
			} else {
				conn = DriverManager.getConnection(getUrl(dbType, ip, port, dbName, jdbcUrl, version,oracleType,sid,serviceName), user, password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 *获取url连接字符串
	 * @param dbType 数据库类型
	 * @param ip   ip地址
	 * @param port 端口
	 * @param dbName  数据库名称
	 * @param jdbcUrl 连接url
	 * @param version sqlserver版本
	 * @param oracleType oracle类型  1服务名  2 sid
	 * @param sid   sid名称
	 * @param serviceName 服务名
	 * @return 拼接好的连接url串
	 */
	private static String getUrl(String dbType, String ip, int port, String dbName, String jdbcUrl, String version,Integer oracleType,String sid,String serviceName)
			throws Exception {
		String url = null;
		if ("oracle".equals(dbType)) {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			if (StringUtils.isNotEmpty(jdbcUrl)) {
				url = jdbcUrl;
			} else if(oracleType==1) {//服务名
				url = "jdbc:oracle:thin:@//" + ip + ":" + port + "/" + serviceName;
			}else if(oracleType==2){ //sid
				url="jdbc:oracle:thin:@" + ip + ":" + port + ":" + sid;
			}
		} else if ("mysql".equals(dbType)) { // mysql
			Class.forName("com.mysql.jdbc.Driver");
			if (jdbcUrl == null || "".equals(jdbcUrl)) {
				url = "jdbc:mysql://" + ip + ":" + port + "/" + dbName;
			} else {
				url = jdbcUrl;
			}
		} else if ("sqlserver".equals(dbType)) {// jdbc:sqlserver://192.168.20.101;DatabaseName=test
			if ("sqlserver 2000".equals(version)) {
				// SQL SERVER 2000
				Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
				if (jdbcUrl == null || "".equals(jdbcUrl)) {
					url = "jdbc:microsoft:sqlserver://" + ip + ":" + port + ";DatabaseName=" + dbName;
				} else {
					url = jdbcUrl;
				}
			} else {// SQL SERVER 2005以及之后
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				if (jdbcUrl == null || "".equals(jdbcUrl)) {
					url = "jdbc:sqlserver://" + ip + ":" + port + ";DatabaseName=" + dbName;
				} else {
					url = jdbcUrl;
				}
			}
		}
		return url;
	}
}
