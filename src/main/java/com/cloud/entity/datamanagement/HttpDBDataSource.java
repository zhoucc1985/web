package com.cloud.entity.datamanagement;

import lombok.Data;

import java.lang.reflect.Field;

@Data
public class HttpDBDataSource {

    //采集平台记录的数据源ID
    private Long source_id;
    //数据库IP地址
    private String host;
    //端口
    private String port;
    //Oracle数据库需要,SID
    private String oracle_param_name;
    //	Oracle数据库需要,0:serverName,1:sid
    private Integer oracle_type;
    // SQLServer数据库需要. 0:sqlserver2005以下 1:sqlserver2005及以上
    private Integer version_type;
    //(数据库文件)SQLlist数据库需要
    private String database_file;
    //用户名
    private String user_name;
    //密码
    private String password;
    //库名
    private String database_name;
    //(数据库类型)MySQL:1,SQLServer:2,Oracle:3,PostgreSQL:4,DB2:5,SQLite:6,MariaDB:7,Sybase:8
    private Integer database_type;
    //数据源名称
    private String source_name;
    //目标库
    private String db_target_name;
    //1:结构化,2:半结构,3非结构(数据库类型)
    private String db_type;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String resultStr="";
        try {
            Field[] fields = this.getClass().getDeclaredFields();
            sb.append("{\r\n");
            for (Field field : fields) {
                sb.append(
                        "\""+field.getName().substring(field.getName().lastIndexOf(".") + 1) + "\":\"")
                        .append(this.getClass().getMethod("get" + field.getName().substring(0, 1).toUpperCase()
                                + field.getName().substring(1)).invoke(this)+"\",")
                        .append("\r\n");
            }
             resultStr=sb.toString().substring(0,sb.length()-1)+"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr;
    }

}
