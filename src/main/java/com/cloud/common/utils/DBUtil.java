package com.cloud.common.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloud.entity.datamanagement.DBDatasource;


/**
 * @Description 关闭Connection
 * @Author daituo
 * @Date 2018-8-29
 **/
public class DBUtil {

	private static final Logger LOG = LoggerFactory.getLogger(DBUtil.class);

	/**
	 * 关闭Connection
	 * 
	 * @param connection
	 */
	public static void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			LOG.error("关闭connection异常:【{}】", e.getLocalizedMessage());
		}
	}

	/**
	 * 关闭ResultSet
	 * 
	 * @param rs
	 */
	public static void closeRs(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			LOG.error("关闭ResultSet异常:【{}】", e.getLocalizedMessage());
		}
	}

	/**
	 * 关闭Statement
	 * 
	 * @param stmt
	 */
	public static void closeStmt(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			LOG.error("关闭Statement异常:【{}】", e.getLocalizedMessage());
		}
	}

	/**
	 * 关闭connetion,statement,rs不需要关闭的可传null
	 * 
	 * @param conn
	 * @param ps
	 * @param rs
	 */
	public static void close(Connection conn, Statement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 获取表的数据量
	 * 
	 * @return
	 */
	public static long getTableNum(Connection conn, String tableName) {
		Statement stmt = null;
		ResultSet rs = null;
		long count = 0l;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from " + tableName);
			while (rs.next()) {
				count = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeRs(rs);
			DBUtil.closeStmt(stmt);
		}
		return count;
	}

	/**
	 * 获取字段不为null的数据量
	 * 
	 * @return
	 */
	public static long getColumnNum(Connection conn, String tableName, String columnName, String dbType) {
		String sql = "";
		if ("mysql".equals(dbType)) {
			sql = "select count(*) from " + tableName + " where " + columnName + "<>''";
		} else {
			sql = "select count(*) from " + tableName + " where " + columnName + " is not null";
		}
		Statement stmt = null;
		ResultSet rs = null;
		long count = 0l;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeRs(rs);
			DBUtil.closeStmt(stmt);
		}
		return count;
	}

}
