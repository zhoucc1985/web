package com.cloud.mapper.real.datacollect;

import com.cloud.entity.datacollect.test;
import com.cloud.entity.datacollect.testExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 请添加描述
 * @time 2019/01/24
 */
public interface RealTestMapper {
    long countByExample(testExample example);

    int deleteByExample(testExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(test record);

    int insertSelective(test record);

    List<test> selectByExample(testExample example);

    test selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") test record, @Param("example") testExample example);

    int updateByExample(@Param("record") test record, @Param("example") testExample example);

    int updateByPrimaryKeySelective(test record);

    int updateByPrimaryKey(test record);
    
    List<Map<String, Object>> getColumnFiledsByTableName(@Param(value = "realTableName") List<String> realTableName);
	
}