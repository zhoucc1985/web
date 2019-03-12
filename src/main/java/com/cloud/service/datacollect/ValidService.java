package com.cloud.service.datacollect;

import java.util.List;
import java.util.Map;

/**
 * 数据验证服务类
 */
public interface  ValidService {
    public boolean  validOne();
    public boolean  VallidList();
    /**
     *  获取列的属性参数
     * @Param tableName 表名称
     */
    List<Map<String,Object>> getColumnFiledsByTableName(String realTableName);

}
