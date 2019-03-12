package com.cloud.controller.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.common.excption.BusinessException;
import com.cloud.common.utils.CommonUtils;
import com.cloud.common.vo.SysPermissionTree;
import com.cloud.entity.sys.OrgInfo;
import com.cloud.entity.sys.SysRole;
import com.cloud.service.sys.SysPermissionService;
import com.cloud.service.sys.SysRoleService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;

/** 
 * @author yuxin
 * @version 创建时间：2018年11月7日 上午10:03:33 
 * 类说明   角色
 */
@RestController
@RequestMapping(value="/api/role")
@Api(tags = "角色", description = "角色管理接口")
public class RoleController {
	
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Resource
	private SysRoleService roleService;
	@Resource
	private SysPermissionService permissionService;
	
	/**
	 * 角色分页列表
	 * @param pageNum 当前页
	 *        pageSize 每页数
	 *        sysRole 角色查询信息
	 * @author yuxin
	 * @date 2018年11月7日 上午11:55:00 
	 * @return
	 */
	@ApiOperation(value = "角色分页列表")
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public Map<String,Object> findRoleList(@RequestParam("pageNum") int pageNum,
			@RequestParam("pageSize")int pageSize,
			@RequestBody SysRole sysRole){
		try {
			PageInfo<SysRole> findRoleList = roleService.findRolePage(pageNum,pageSize,sysRole);
			return CommonUtils.getSucResultMap(findRoleList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询角色列表异常",e);
			return CommonUtils.getErrorResultMap("查询角色列表异常");
		}
	}
	
	/**
	 * 查询当前机构的所有角色(用户的新增和编辑时)
	 * @param
	 * @author yuxin
	 * @date 2018年11月21日 下午4:09:49 
	 * @return
	 */
	@ApiOperation(value = "查询机构的所有角色")
	@RequestMapping(value="/current/roleInfo",method=RequestMethod.POST)
	public Map<String, Object> findRoleByOrgId(@RequestBody OrgInfo org){
		try {
			List<SysRole> findAllRole = roleService.findRoleByOrgId(org);
			return CommonUtils.getSucResultMap(findAllRole);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("由机构查询角色异常",e);
			return CommonUtils.getErrorResultMap();
		}
	}
	
	/**
	 * 查询机构及以下机构的角色
	 * @param
	 * @author yuxin
	 * @date 2018年11月21日 下午4:09:49 
	 * @return
	 */
	@ApiOperation(value = "查询机构及以下机构的角色")
	@RequestMapping(value="/orgCode/roleInfo",method=RequestMethod.GET)
	public Map<String, Object> findRoleByOrgCode(){
		try {
			List<SysRole> findAllRole = roleService.findCurAndSonRole();
			return CommonUtils.getSucResultMap(findAllRole);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询机构及以下机构的角色",e);
			return CommonUtils.getErrorResultMap();
		}
	}
	
	/**
	 * 新增角色
	 * @param role 角色信息
	 * @author yuxin
	 * @date 2018年11月7日 下午12:06:32 
	 * @return
	 */
	@ApiOperation(value = "新增角色")
	@RequestMapping(value="",method=RequestMethod.POST)
	public Map<String, Object> addRole(@RequestBody SysRole role){
		try {
			roleService.addRole(role);
			return CommonUtils.getSucResultMap("新增角色成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增角色异常",e);
			return CommonUtils.getErrorResultMap("新增角色异常:"+e.getLocalizedMessage());
		}
	}
	
	/**
	 * 更新角色
	 * @param role 角色信息
	 * @author yuxin
	 * @date 2018年11月7日 下午2:46:32 
	 * @return
	 */
	@ApiOperation(value = "更新角色")
	@RequestMapping(value="",method=RequestMethod.PUT)
	public Map<String, Object> updateRole(@RequestBody SysRole role){
		try {
			roleService.updateRole(role);
			return CommonUtils.getSucResultMap("更新角色成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新角色异常",e);
			return CommonUtils.getErrorResultMap("更新角色异常:"+e.getLocalizedMessage());
		}
	}
	
	/**
	 * 删除角色
	 * 
	 * @param roleIds
	 *            角色ids
	 * @author yuxin
	 * @date 2018年11月7日 下午2:51:10
	 * @return
	 */
	@ApiOperation(value = "删除角色")
	@RequestMapping(value = "/del", method = RequestMethod.DELETE)
	public Map<String, Object> delRole(@RequestBody List<Long> roleIds) {
		try {
			roleService.delRole(roleIds);
			return CommonUtils.getSucResultMap("删除角色成功");
		} catch (BusinessException busException) {
			return CommonUtils.getWarnResultMap(busException
					.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除角色异常", e);
			Map<String, Object> errorResultMap = CommonUtils
					.getErrorResultMap("删除角色异常:" + e.getLocalizedMessage());
			return errorResultMap;
		}

	}
	
	/**
	 * 由角色id查询角色权限树
	 * @param id 角色id
	 * @param roleType 角色类型
	 * @author yuxin
	 * @date 2018年11月13日 下午2:34:38 
	 * @return
	 */
	@ApiOperation(value = "由角色id查询角色权限树")
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Map<String,Object> findRolePerById(@PathVariable Long id,
			@RequestParam(value="roleType")String roleType){
		try {
			SysPermissionTree permisssionTree = permissionService.getPermissionTree(id,roleType);
			return CommonUtils.getSucResultMap(permisssionTree);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询权限信息异常");
			return CommonUtils.getErrorResultMap("查询权限信息异常");
		}
	}
	
	/**
	 * 修改角色权限
	 * @param ids 保存的权限ids
	 * @param roleId 被修改的角色id
	 * @author yuxin
	 * @date 2018年11月13日 下午5:50:34 
	 * @return
	 */
	@ApiOperation(value = "修改角色权限")
	@RequestMapping(value="/authority/{roleId}",method=RequestMethod.PUT)
	public Map<String, Object> updatePermission(@RequestBody List<Long> ids,
			@PathVariable(value="roleId")Long roleId){
		try {
			permissionService.updatePermission(ids, roleId);
			return CommonUtils.getSucResultMap("权限修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改角色权限异常");
			return CommonUtils.getErrorResultMap("修改角色权限异常");
		}
	}
	
	

}
