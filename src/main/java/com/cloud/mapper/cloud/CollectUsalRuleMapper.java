package com.cloud.mapper.cloud;


import com.cloud.entity.cloud.CollectUsalRule;

/**
* 请添加描述
* @author zhuJT
* @time 2019/02/14
*/
public interface CollectUsalRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CollectUsalRule record);

    int insertSelective(CollectUsalRule record);

    CollectUsalRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CollectUsalRule record);

    int updateByPrimaryKey(CollectUsalRule record);
}