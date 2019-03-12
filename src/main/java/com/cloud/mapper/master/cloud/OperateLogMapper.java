package com.cloud.mapper.master.cloud;

import com.cloud.entity.cloud.OperateLog;

/**
 * 日志操作mapper
 * @author zhoucc
 **/
public interface OperateLogMapper {
    /**
     * 根据id删除
     * @param id id
     * @return int
     * @author zhoucc
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 根据id插入
     * @param record
     * @return int
     * @author zhoucc
     */
    int insert(OperateLog record);

    /**
     * 插入日志记录
     * @param record
     * @return int
     * @author zhoucc
     */
    int insertSelective(OperateLog record);

    /**
     * 根据主键id查询
     * @param id
     * @return int
     * @author zhoucc
     */
    OperateLog selectByPrimaryKey(Long id);

    /**
     * 更新日志记录
     * @param record
     * @return int
     * @author zhoucc
     */
    int updateByPrimaryKeySelective(OperateLog record);

    /**
     * 更新日志记录
     * @param record
     * @return int
     * @author zhoucc
     */
    int updateByPrimaryKey(OperateLog record);
}