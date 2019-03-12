package com.cloud.mapper.master.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cloud.entity.sys.SysRole;

/**
* 请添加描述  角色
* @author yuxin
* @time 2018/11/06
*/
public interface SysRoleMapper {
	/**
	 * 根据id删除角色
	 * @param id 角色id
	 * @author yuxin
	 * @date 2018年11月13日 下午7:04:13 
	 * @return
	 */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增角色
     * @param record
     * @author yuxin
     * @date 2018年11月13日 下午7:04:35 
     * @return
     */
    int insert(SysRole record);

    /**
     * 新增角色
     * @param record
     * @author yuxin
     * @date 2018年11月13日 下午7:04:49 
     * @return
     */
    int insertSelective(SysRole record);

    /**
     * 由角色id查询角色
     * @param id 角色id
     * @author yuxin
     * @date 2018年11月13日 下午7:05:04 
     * @return
     */
    SysRole selectByPrimaryKey(Long id);

    /**
     * 由角色id更新角色
     * @param record
     * @author yuxin
     * @date 2018年11月13日 下午7:05:20 
     * @return
     */
    int updateByPrimaryKeySelective(SysRole record);

    /**
     * 更新角色
     * @param record
     * @author yuxin
     * @date 2018年11月13日 下午7:05:46 
     * @return
     */
    int updateByPrimaryKey(SysRole record);
    
    /**
     * 由用户id查询角色
     * @param  uId 用户id
     * @return
     */
    
    List<SysRole> selectRoleNameByUserId(@Param(value = "uId") Long uId);

    /**
     * 获取角色列表
     * @param orgCode 组织机构code
     * @param orgId 组织机构id
     * @param roleName 角色名称
     * @param roleType 角色类型
     * @author yuxin
     * @date 2018年11月18日 下午7:05:46 
     * @return
     */
    List<SysRole> findRoleList(@Param(value="orgCode")String orgCode,
    		@Param(value="orgId")Long orgId,
    		@Param(value="roleName")String roleName,
    		@Param(value="roleType")String roleType);

    /**
     * 由角色名称查询角色
     * @param roleName 角色名称
     * @param orgId 机构id
     * @param orgCode 机构code
     * @author yuxin
     * @return
     */
    List<SysRole> findRoleByNameAndOrgId(@Param(value="roleName")String roleName,
    		@Param(value="orgId")Long orgId,
    		@Param(value="orgCode")String orgCode);
    
    /**
     * 查询该机构的所有角色
     * @param orgId 机构id
     * @param roleName 角色名称
     * @param roleType 角色类型
     * @author yuxin
     * @date 2018年11月21日 下午4:03:53 
     * @return
     */
    List<SysRole> findRoleByOrgId(@Param(value="orgId")Long orgId,
                                  @Param(value="roleName")String roleName,
	                              @Param(value="roleType")String roleType);
    
    /**
     * 根据机构code查询角色
     * @param code 机构code
     * @author yuxin
     * @date 2018年11月22日 上午11:09:22 
     * @return
     */
    List<SysRole> findRoleByOrgCode(@Param(value="code")String code);
    
}