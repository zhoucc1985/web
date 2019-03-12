package com.cloud.entity.datamanagement;

import lombok.Data;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Data
public class DescJson {

    //采集平台记录的数据源ID
    private Long source_id;
    //数据库IP地址
    private String tableName;
    //端口
    private Map<String,List<String>> data;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"source_id\":").append("\""+source_id+"\"").append(",");
        sb.append("\"tableName\":").append("\""+tableName+"\"").append(",");
        sb.append("\"date\":").append(mapToString(data));
        sb.append("}");
        return sb.toString();
    }
    public String  mapToString(Map<String,List<String>> map){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(String key:map.keySet()){
            List<String> list=map.get(key);
            sb.append(key+":\""+list.toString()+"\",");
        }
        String result=sb.toString().substring(0,sb.length()-1);
        result+="}";
        return result;
    }

}
