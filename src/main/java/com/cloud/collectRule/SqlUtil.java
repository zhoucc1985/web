//package com.cloud.collectRule;
//
//import java.io.BufferedReader;
//import java.sql.Clob;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.Statement;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.sun.org.apache.bcel.internal.generic.NEW;
//
//import pub.dbDialectFactory.DialectFactory;
//import pub.servlet.ConfigInit;
//import pub.source.DatabaseUtil;
//import pub.source.LogOperateUtil;
//import util.StringUtil;
//
//public class SqlUtil {
//	public static String toString(Object obj) {
//		String retunStr ="";
//		String str = obj != null ? obj.toString() : "";
//		if(!"".equals(str)){
//			if(str.endsWith(".0")){
//				retunStr = str.substring(0,str.length()-2);
//			}else{
//				retunStr = str;
//			}
//		}
//		return retunStr;
//	}
//	/**
//	 * 去掉sql语句中存在空字符串的，设置为null，防止插入控制符
//	 * @param str
//	 * @return String
//	 */
//	public static String removeBlankSQL(String sql) {
//		return sql.replaceAll("''", "null");
//	}
//
//	/**
//	 * 新增/修改时获取数字
//	 * @param str
//	 * @return String
//	 */
//	public static String getNumber(String str) {
//		if (str == null || str == "null" || str.trim().length()==0 ) {
//			return null;
//		}
//		return str;
//	}
//
//	/**
//	 * 新增/修改时获取字符串
//	 * @param str
//	 * @return String
//	 */
//	public static String getValue(String str) {
//		if (str == null || str == "null" ) {
//			return null;
//		}
//		return "'" + str + "'";
//	}
//
//
//	/**
//	 * 新增/修改时获取时间及其对应格式
//	 * @param str
//	 * @param format
//	 * @return
//	 */
//	public static String getDateString(String str) {
//		if (str.length() == 4) {
//			str += "-01-01 00:00:00";
//		}else if (str.length() == 7) {
//			str += "-01 00:00:00";
//		}else if (str.length() == 10) {
//			str += " 00:00:00";
//		}else if (str.length() == 13) {
//			str += ":00:00";
//		}else if (str.length() == 16) {
//			str += ":00";
//		}
//		return str;
//	}
//
//
//	/**
//	 * 日期字符串运算
//	 * @param str 传入的日期字符串
//	 * @param formate 获取的日期格式
//	 * @param type 运算类型
//	 * @param number 运算数值
//	 * @return
//	 */
//	public static String addDate(String str,String formate,String type,int number) {
//		SimpleDateFormat format = new SimpleDateFormat(formate);
//		Calendar calendar=Calendar.getInstance();
//		Date date = null;
//		try {
//			date = format.parse(str);
//			calendar.setTime(date);
//			if(type.equals("yyyy")){
//				calendar.add(Calendar.YEAR, number);
//			}else if(type.equals("MM")){
//				calendar.add(Calendar.MONTH, number);
//			}else if(type.equals("dd")){
//				calendar.add(Calendar.DAY_OF_MONTH, number);
//			}else if(type.equals("HH")){
//				calendar.add(Calendar.HOUR_OF_DAY, number);
//			}else if(type.equals("mm")){
//				calendar.add(Calendar.MINUTE, number);
//			}else if(type.equals("ss")){
//				calendar.add(Calendar.SECOND, number);
//			}
//		  } catch (Exception e) {
//			  System.out.println(e.getMessage());
//			   }
//		return format.format(calendar.getTime());
//	}
//
//	/**
//	 * 获取算平均数据的开始时间和结束时间
//	 * @param DT yyyy-MM-dd
//	 * @return
//	 */
//	public static String[] getAvgStAndEndTime(String DT, String returnDateFormat){
//		String[] stAndEndTime = new String[2];
//		if(!StringUtil.checkStr(DT))
//			return null;
//		try {
//			String avgTime = ConfigInit.Config.getProperty("AVG_TIME");
//			avgTime = (avgTime == null) ? "20:00:00" : avgTime;
//			String avgTimeSpan = ConfigInit.Config.getProperty("AVG_TIME_SPAN");
//			avgTimeSpan = (avgTimeSpan == null) ? "-24" : avgTimeSpan;
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(format.parse(DT + " " + avgTime));
//			calendar.add(Calendar.HOUR, StringUtil.toInt(avgTimeSpan));
//			if(avgTimeSpan.startsWith("-")){
//				stAndEndTime[0] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
//				stAndEndTime[1] = DT + " " + avgTime;
//			}else{
//				stAndEndTime[0] = DT + " " + avgTime;
//				stAndEndTime[1] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
//			}
//			SimpleDateFormat returnDataFormat = new SimpleDateFormat(returnDateFormat);
//			stAndEndTime[0] = returnDataFormat.format(format.parse(stAndEndTime[0]));
//			stAndEndTime[1] = returnDataFormat.format(format.parse(stAndEndTime[1]));
//		}catch (Exception e) {
//			LogOperateUtil.logException(new RuntimeException("时间转换出错，请检查参数是否正确及配置文件是否配置正确！"));
//			e.printStackTrace();
//		}
//		return stAndEndTime;
//	}
//
//	/*******************************************************************************************************************/
//	/**  加入以下方法是在使用mysql时，查询分组前N条数据时，使用动态sql，                                                                                                                                    **/
//	/**  会出现动态参数无法初始化，所以修改使用动态sql时在同个会话中先初始化动态参数。                                                                                                  **/
//	/*************************************** begin *********************************************************************/
//
//	/**
//	 * PrepareStatement预处理分页查询，解决SQl注入问题
//	 * @param sql 查询SQL
//	 * @param page 当前页
//	 * @param limit 每页记录数
//	 * @param list 查询参数
//	 * @param domain 数据源
//	 * @param reSetSql 初始化动态参数sql
//	 * @return 查询结果集
//	 */
//	@SuppressWarnings({ "unused", "unchecked" })
//	public static List queryForListByPage(String sql, int page, int limit, List list, String domain, String reSetSql) {
//		Map<String, String> params = DatabaseUtil.before(sql, list, domain);
//		Connection conn = null;
//		ResultSet rs = null;
//		List<Map> tempList = new ArrayList<Map>();
//		conn = DatabaseUtil.getConnection(domain);
//		Statement st = null;
//		PreparedStatement sta = null;
//		int rowId = 0;
//		try {
//			String[] sqlArr = {
//					reSetSql,
//					sql
//			};
//			//假如from前是一个换行符而不是一个空格，会导致sybase分页方言里报错
//			sql = sql.replace("\n", " ").replace("\r", " ");
//			// 方言
//			Object objs[] = DialectFactory.getDialect(domain)
//					.getDataByPageEoms(conn, sqlArr, page, limit, list);
//			if(objs == null){
//				return tempList;
//			}
//			st = (Statement) objs[0];
//			rs = (ResultSet) objs[1];
//			rowId = resultToList(tempList, conn, rs, null);
//		} catch (Exception e) {
//			LogOperateUtil.logSQLError(e, domain, params.get("sql1"), params.get("path"));
//		} finally {
//			DatabaseUtil.free(conn, st, rs);
//		}
//		DatabaseUtil.after(params, rowId);
//		return tempList;
//	}
//
//	/**
//	 * PrepareStatement SQL查询
//	 *
//	 * @param sql 查询SQL
//	 * @param list 查询参数
//	 * @param domain 数据源
//	 * @return 查询结果集
//	 */
//	@SuppressWarnings("unchecked")
//	public static List queryForList(String sql, List list, String domain, String reSetSql ) {
//		Map<String, String> params = DatabaseUtil.before(sql, list, domain);
//		Connection conn = null;
//		PreparedStatement st = null;
//		ResultSet rs = null;
//		List<Map> tempList = new ArrayList<Map>();
//		conn = DatabaseUtil.getConnection(domain);
//		int rowId = 0;
//		try {
//
//			st = conn.prepareStatement(reSetSql);
//			st.executeUpdate();
//
//			st = conn.prepareStatement(sql);
//			if(list !=null && list.size() > 0){
//				int size = list.size();
//				for(int i = 0; i < size; i++){
//					st.setObject(i+1, list.get(i));
//				}
//			}
//
//			rs = st.executeQuery();
//			rowId = resultToList(tempList, conn, rs, null);
//		} catch (Exception e) {
//			LogOperateUtil.logSQLError(e, domain, params.get("sql1"), params.get("path"));
//		} finally {
//			DatabaseUtil.free(conn, st, rs);
//		}
//		DatabaseUtil.after(params, rowId);
//		return tempList;
//	}
//
//	/**限制查询结果数据行*/
//	private static final int TOTAL=10000;
//
//	/**
//	 * 把结果集转变成 List<Map>对象
//	 * @param tempList 用于装载结果的list
//	 * @param conn 连接对象
//	 * @param rs 结果集对象
//	 */
//	@SuppressWarnings("unchecked")
//	private static int resultToList(List<Map> tempList, Connection conn, ResultSet rs, String totalCount) throws Exception{
//		boolean hasTotalCount = StringUtil.checkStr(totalCount);
//		int rowId = 0;
//		ResultSetMetaData meta = rs.getMetaData();
//		int columnCount = meta.getColumnCount();
//		String colValue =null;
//		String dbType = conn.getMetaData().getDatabaseProductName();
//		while (rs.next()) {
//			rowId++;
//			Map<String, String> map = new LinkedHashMap<String, String>();
//			for (int i = 1; i <= columnCount; i++) {
//				String colName = meta.getColumnLabel(i);
//				colValue = (meta.getColumnTypeName(i).equalsIgnoreCase("CLOB"))?readClob(rs.getObject(i)):rs.getString(i);
//				colValue = formatNumberValue(meta.getColumnTypeName(i),colValue,dbType);
//				map.put(colName, colValue);
//			}
//			map.put("rowId", rowId + "");
//			if(hasTotalCount){
//				map.put("totalCount", totalCount); //装入总记录数
//				map.put("iRecCount", totalCount); //装入总记录数
//			}
//			tempList.add(map);
//			if(rowId>TOTAL)break;//如果数据超出指定的条数将强制退出循环
//		}
//
//		return rowId;
//	}
//
//	/**
//	 * 处理oracle数值型为小数时会省掉小数点前面的0的情况，在前面自动加上0
//	 * tangyj 2013-01-06
//	 * @param columnTypeName  列类型名称
//	 * @param columnValue 列值
//	 * @param dbType 数据库类型
//	 * @return 如：0.12
//	 */
//	private static String formatNumberValue(String columnTypeName,String columnValue,String dbType) {
//		if("Oracle".equals(dbType)){
//			if(columnTypeName.equalsIgnoreCase("NUMBER") &&  columnValue !=null && (columnValue.trim().startsWith(".") || columnValue.trim().startsWith("-."))){
//				StringBuilder sb = new StringBuilder(columnValue.trim());
//				sb.insert(columnValue.trim().indexOf("."), "0");
//				columnValue=sb.toString();
//			}
//		}
//		return columnValue;
//	}
//
//	/**
//	 * CLOB字段读取
//	 * @param obj 数据对象
//	 * @return 结果文本
//	 */
//	private static String readClob(Object obj){
//		if(obj==null)return "";
//		StringBuffer mystr=new StringBuffer("");
//		String str = "";
//		BufferedReader a=null;
//		try{
//			a=new BufferedReader(((Clob)obj).getCharacterStream()); //以字符流的方式读入BufferedReader
//			int flg = 0;
//			while ((str = a.readLine()) != null) {
//				if(flg > 0){
//					mystr.append("\n");
//				}
//				mystr.append(str);
//				flg++;
//			}
//		}catch(Exception e){
//			return null;
//		}finally{
//			try{
//				if(a!=null)a.close();
//			}catch(Exception ex){}
//		}
//		return mystr.toString();
//	}
//
//	/******************************************** end ******************************************************************/
//
//}
