package com.cloud.mapper.temp.datacollect;

import com.cloud.entity.datacollect.test;
import com.cloud.entity.datacollect.testExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 请添加描述
 * @time 2019/01/24
 */
public interface TempTestMapper {
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
}