package com.cloud.mapper.master.file;


import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* 请添加描述
* @author zhuJT
* @time 2019/01/25
*/
public interface FileMapper {

    String selectByFileName(@Param("fileName") String fileName);

    Map<String,Object> selectSysNameCode(String sysName);

    String selectFirstCode(@Param("firstName") String firstName, @Param("parentId") Integer parentId);

    String selectSecondCode(@Param("secondName") String secondName, @Param("parentId") Integer parentId);
}