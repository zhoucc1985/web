package com.cloud.mapper.master.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cloud.entity.sys.SysUser;

/**
* 请添加描述 用户
* @author yuxin
* @time 2018/11/12
*/
public interface SysUserMapper {
	/**
	 * 根据id删除用户
	 * @param id 用户id
	 * @author yuxin
	 * @date 2018年11月13日 下午6:56:47 
	 * @return
	 */
    int deleteByPrimaryKey(Long id);
    /**
     * 新增用户
     * @param record
     * @author yuxin
     * @date 2018年11月13日 下午6:57:15 
     * @return
     */
    int insert(SysUser record);
    /**
     * 新增用户
     * @param record
     * @author yuxin
     * @date 2018年11月13日 下午6:58:02 
     * @return
     */
    int insertSelective(SysUser record);
    
    /**
     * 获得用户信息通过用户id(包含密码)
     * @param id 用户id
     * @author yuxin
     * @date 2018年11月19日 上午11:12:04 
     * @return
     */
    SysUser selectInfoById(@Param(value="id")Long id);

    /**
     * 查询用户
     * @param id 用户id
     * @author yuxin
     * @date 2018年11月13日 下午6:58:22 
     * @return
     */
    SysUser selectByPrimaryKey(@Param(value="id")Long id);

    /**
     * 更新用户
     * @param record
     * @author yuxin
     * @date 2018年11月13日 下午6:58:40 
     * @return
     */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     * 根据id更新用户
     * @param record
     * @author yuxin
     * @date 2018年11月13日 下午6:58:56 
     * @return
     */
    int updateByPrimaryKey(SysUser record);
    /**
     * 根据用户名称查询用户信息
     * @param username 用户名
     * @return
     */
    SysUser findByUsername(String username);

    /**
     * 查询该角色下是否有用户
     * @param roleId 角色id
     * @author yuxin
     * @date 2018年11月13日 下午7:07:44 
     * @return
     */
    int findUserByRoleId(@Param(value="roleId")Long roleId);

    /** 
    * 查询该用户下是否有组织机构
    * @param  orgId 组织机构id
    * @return com.cloud.entity.sys.SysUser 
    * @author huangYl
    * @date   2018/11/15 14:09
    */ 
    SysUser selectUserByOrgId(Long orgId);
    
    /**
     * 查询用户列表
     * @param  loginName 用户名称 
     * @param  orgCode 机构code
     * @param  roleId 角色id
     * @param  status 用户状态
     * @author yuxin
     * @date 2018年11月15日 下午2:54:35 
     * @return
     */
    List<SysUser> findUserList(@Param(value="loginName")String loginName,
    		@Param(value="orgCode")String orgCode,
    		@Param(value="roleId")Long roleId,
    		@Param(value="status")String status);
    
    /**
     * 验证账号名称唯一性
     * @param loginName 登录名
     * @author yuxin
     * @date 2018年11月14日 下午6:24:10 
     * @return
     */
    List<SysUser> checkLoginName(@Param(value="loginName")String loginName);
    
}