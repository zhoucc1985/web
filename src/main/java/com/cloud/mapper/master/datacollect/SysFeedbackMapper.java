package com.cloud.mapper.master.datacollect;

import org.apache.ibatis.annotations.Param;

import com.cloud.entity.datacollect.sysFeedback;

/**
* 请添加描述
* @author zhuJT
* @time 2019/02/26
*/
public interface SysFeedbackMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(sysFeedback record);

    int insertSelective(sysFeedback record);

    sysFeedback selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(sysFeedback record);

    int updateByPrimaryKey(sysFeedback record);
    
    sysFeedback selectByUserId(@Param("userId") Integer userId);
    
    int updateSysFeedback(sysFeedback record);
    
}