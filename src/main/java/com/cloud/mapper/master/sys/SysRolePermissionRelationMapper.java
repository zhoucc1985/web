package com.cloud.mapper.master.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cloud.entity.sys.SysRolePermissionRelation;

/**
* 请添加描述 角色权限关系
* @author yuxin
* @time 2018/11/06
*/
public interface SysRolePermissionRelationMapper {
	/**
	 * 根据id删除角色权限关系
	 * @param id 关联id
	 * @author yuxin
	 * @date 2018年11月13日 下午7:01:16 
	 * @return
	 */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增角色权限关联
     * @param record
     * @author yuxin
     * @date 2018年11月13日 下午7:01:47 
     * @return
     */
    int insert(SysRolePermissionRelation record);

    /**
     * 新增角色权限关联
     * @param record
     * @author yuxin
     * @date 2018年11月13日 下午7:02:11 
     * @return
     */
    int insertSelective(SysRolePermissionRelation record);

    /**
     * 由关联id查询关联关系
     * @param id 关联id
     * @author yuxin
     * @date 2018年11月13日 下午7:02:29 
     * @return
     */
    SysRolePermissionRelation selectByPrimaryKey(Long id);

    /**
     * 根据id更新关联关系
     * @param record
     * @author yuxin
     * @date 2018年11月13日 下午7:02:52 
     * @return
     */
    int updateByPrimaryKeySelective(SysRolePermissionRelation record);

    /**
     * 跟新关联关系
     * @param record
     * @author yuxin
     * @date 2018年11月13日 下午7:03:10 
     * @return
     */
    int updateByPrimaryKey(SysRolePermissionRelation record);
    
    /**
     * 删除该角色下的权限关联
     * @param id 角色id
     * @author yuxin
     * @date 2018年11月13日 下午6:55:30 
     * @return
     */
    int delByRoleId(Long id);
    
    /**
     * 批量新增权限关联
     * @param list 权限关系集合
     * @author yuxin
     * @date 2018年11月16日 下午2:27:14 
     * @return
     */
    int batchInsert(@Param(value="list")List<SysRolePermissionRelation> list);
}