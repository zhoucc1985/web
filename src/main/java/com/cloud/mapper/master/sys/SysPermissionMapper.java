package com.cloud.mapper.master.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cloud.entity.sys.SysPermission;

/**
* 请添加描述  权限
* @author yuxin
* @time 2018/11/12
*/
public interface SysPermissionMapper {
	/**
	 * 由权限id删除权限
	 * @param id 权限id
	 * @author yuxin
	 * @date 2018年11月13日 下午7:08:10 
	 * @return
	 */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增权限
     * @param record
     * @author yuxin
     * @date 2018年11月13日 下午7:08:36 
     * @return
     */
    int insert(SysPermission record);

    /**
     * 新增权限
     * @param record
     * @author yuxin
     * @date 2018年11月13日 下午7:08:45 
     * @return
     */
    int insertSelective(SysPermission record);

    /**
     * 由权限id查询权限
     * @param id权限id
     * @author yuxin
     * @date 2018年11月13日 下午7:09:00 
     * @return
     */
    SysPermission selectByPrimaryKey(Long id);

    /**
     * 由权限id更新权限
     * @param record
     * @author yuxin
     * @date 2018年11月13日 下午7:09:16 
     * @return
     */
    int updateByPrimaryKeySelective(SysPermission record);

    /**
     * 更新权限
     * @param record
     * @author yuxin
     * @date 2018年11月13日 下午7:09:30 
     * @return
     */
    int updateByPrimaryKey(SysPermission record);

    /**
     * 根据用户id获取用户权限
     * @param userId
     * @return
     */
    List<SysPermission> findPermissionNameByUserId(@Param(value = "userId") Long userId);

    /**
     * 根据角色id获取用户权限
     * @param roleId
     * @param permissionType
     * @return
     */
    List<SysPermission> findPermissionNameByRoleId(@Param(value = "roleId") Long roleId,
    		@Param(value="permissionType")String permissionType);
    
    
    /**
     * 查询该角色可分配的权限
     * @param roleType 角色类型
     * @author yuxin
     * @date 2018年11月14日 上午11:24:17 
     * @return
     */
    List<SysPermission> findPremissionObtainByRoleType(@Param(value="roleType")String roleType);
    
    /**
     * 获取所有权限
     * @param
     * @author yuxin
     * @date 2018年11月16日 下午2:37:23  
     * @return
     */
    List<SysPermission> findAll();

}