package com.cloud.service.sys;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cloud.common.dict.CommonDict;
import com.cloud.common.excption.BusinessException;
import com.cloud.common.utils.ObjectUtils;
import com.cloud.common.utils.UserUtils;
import com.cloud.common.vo.ShiroUser;
import com.cloud.common.vo.sys.org.OrgDetailVo;
import com.cloud.entity.sys.OrgInfo;
import com.cloud.entity.sys.SysRole;
import com.cloud.mapper.master.sys.OrgInfoMapper;
import com.cloud.mapper.master.sys.SysRoleMapper;
import com.cloud.mapper.master.sys.SysRolePermissionRelationMapper;
import com.cloud.mapper.master.sys.SysUserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 添加文件描述
 *
 * @author Pan jianneng
 * @time 2018/10/19 14:45
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleService {

	@Resource
	private SysRoleMapper roleMapper;
	@Resource
	private SysUserMapper userMapper;
	@Resource
	private SysRolePermissionRelationMapper relationMapper;
	@Resource
	private OrgInfoMapper orgInfoMapper;
	@Resource
	private OrgInfoService orgInfoService;

	/**
	 * @description: 根据当前登录用户id获取角色名称列表
	 * @param: uId 用户id
	 * @return:
	 * @author: huangYl
	 * @date: 2018/10/30 9:52
	 */
	public List<SysRole> selectRoleNameByUserId(Long uId) {
		List<SysRole> roles = roleMapper.selectRoleNameByUserId(uId);
		if (null == roles) {
			return new ArrayList<>();
		}
		return roles;
	}

	/**
	 * @description: 根据当前登录用户id获取角色名称
	 * @param: id 用户id
	 * @return: com.cloud.entity.sys.SysRole
	 * @author: huangYl
	 * @date: 2018/11/6 18:57
	 */
	public SysRole selectByUserId(Long id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	/**
	 * 获取角色列表
	 * 
	 * @param pageNum 当前页
	 *        pageSize 每页数
	 *        sysRole 角色查询信息
	 * @author yuxin
	 * @date 2018年11月7日 上午10:40:46
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class,readOnly=true)
	public PageInfo<SysRole> findRolePage(int pageNum, int pageSize,SysRole sysRole) {
		// 当前登录用户
		ShiroUser currentlyUserInfo = UserUtils.getInstance()
				.getCurrentlyUserInfo();
		List<SysRole> roleList=null;
		//学校能看到全校角色
		if(CommonDict.ORG_TYPE_SCHOOL.equals(sysRole.getOrgType())){
			PageHelper.startPage(pageNum, pageSize);
			roleList = roleMapper.findRoleList(sysRole.getOrgCode(),null,sysRole.getRoleName(), sysRole.getRoleType());
		}//当前机构只能看到当前机构的角色 
		else {
			PageHelper.startPage(pageNum, pageSize);
			roleList = roleMapper.findRoleList("",sysRole.getOrgId(),sysRole.getRoleName(), sysRole.getRoleType());
		}
		long orgId=currentlyUserInfo.getOrgId().longValue();
		for (SysRole role : roleList) {
	    	if(ObjectUtils.isNotNullObject(currentlyUserInfo)){
	        	if (orgId==role.getOrgId().longValue()
	        			&& role.getDefaultRole()){
	    		    role.setDefaultRole(true);
	    		}else{
	    			 role.setDefaultRole(false);
	    		}
	    	}
		}
		return new PageInfo<SysRole>(roleList);
	}

	/**
	 * 获取机构及以下的所有角色
	 * 
	 * @author yuxin
	 * @date 2018年11月7日 上午10:43:01
	 * @return
	 */
	public List<SysRole> findCurAndSonRole() {
		// 当前登录用户
		ShiroUser currentlyUserInfo = UserUtils.getInstance()
						.getCurrentlyUserInfo();
		List<SysRole> roleList = roleMapper.findRoleList(currentlyUserInfo.getOrgCode(),null, "", "");
		return roleList;
	}
	
	/**
	 * 获取当前机构的所有角色(用户的新增和编辑时)
	 * 
	 * @param
	 * @author yuxin
	 * @date 2018年11月7日 上午10:43:01
	 * @return
	 */
	public List<SysRole> findRoleByOrgId(OrgInfo org) {
		List<SysRole> roleList=null;
		// 学校下的部门能看到全校角色
		if (CommonDict.ORG_TYPE_DEPARTMENT.equals(org.getType())) {
			// 将机构号设置成学校机构号(学校下只有一级部门)
			OrgDetailVo currentAndParentOrg = orgInfoMapper.getCurrentAndParentOrg(org.getId());
			roleList=roleMapper.findRoleList(currentAndParentOrg.getParentCode(),null, "", "");
		}//学校能看到全校的角色
		else if(CommonDict.ORG_TYPE_SCHOOL.equals(org.getType())){
			roleList = roleMapper.findRoleList(org.getCode(),null, "", "");
		}//当前机构只能看到当前机构的角色 
		else {
			roleList = roleMapper.findRoleByOrgId(org.getId(),"","");
		}
		return roleList;
	}
	
	/**
	 * 新增角色
	 * 
	 * @param role
	 *            角色信息
	 * @author yuxin
	 * @date 2018年11月7日 上午11:53:39
	 * @return
	 * @throws Exception 
	 */
	public void addRole(SysRole role) throws Exception{
		ShiroUser currentlyUserInfo = UserUtils.getInstance().getCurrentlyUserInfo();
		role.setOrgId(currentlyUserInfo.getOrgId());
		//验证角色信息
		validate(role,currentlyUserInfo);
		roleMapper.insertSelective(role);
	}
	
	/**
	 * 根据角色id批量删除角色
	 * 
	 * @param ids
	 *            角色ids
	 * @author yuxin
	 * @date 2018年11月7日 下午2:38:15
	 * @return
	 * @throws Exception
	 */
	public Boolean delRole(List<Long> ids) throws Exception {
		if (ObjectUtils.isNotEmptyList(ids)) {
			for (Long id : ids) {
				//该角色下是否存在用户
				int exitsUser = userMapper.findUserByRoleId(id);
				if(exitsUser>0){
					throw new BusinessException("存在用户数据，不能进行删除！");
				}
				//删除角色id下的权限关联
				relationMapper.delByRoleId(id);
				roleMapper.deleteByPrimaryKey(id);
			}
			return true;
		} else {
			throw new Exception("请至少选择一条数据！");
		}
	}

	/**
	 * 修改角色
	 * 
	 * @param role
	 *            角色信息
	 * @author yuxin
	 * @date 2018年11月7日 下午2:39:51
	 * @return
	 * @throws Exception 
	 */
	public void updateRole(SysRole role) throws Exception {
		ShiroUser currentlyUserInfo = UserUtils.getInstance().getCurrentlyUserInfo();
		//验证角色信息
		validate(role,currentlyUserInfo);
		//判断该角色类别是否变更
		SysRole oldRole = roleMapper.selectByPrimaryKey(role.getId());
		if(!oldRole.getRoleType().equals(role.getRoleType())){
			//角色类别变了  则清空以前角色的权限关联
			relationMapper.delByRoleId(role.getId());
		}
		int updateByPrimaryKey = roleMapper.updateByPrimaryKeySelective(role);
		if(updateByPrimaryKey<=0){
			throw new Exception("更新失败！");
		}
	}
	
	/**
	 * 验证角色信息
	 * @param role 角色
	 * @author yuxin
	 * @date 2018年11月8日 下午4:06:28 
	 * @return
	 */
	public void validate(SysRole role, ShiroUser currentlyUserInfo)
			throws Exception {
		List<SysRole> roleByNameAndOrgId = null;
		if (CommonDict.ORG_TYPE_DEPARTMENT.equals(currentlyUserInfo
				.getOrgType())
				|| CommonDict.ORG_TYPE_SCHOOL.equals(currentlyUserInfo
						.getOrgType())) {
			roleByNameAndOrgId = roleMapper.findRoleByNameAndOrgId(
					role.getRoleName(), null,
					currentlyUserInfo.getSchoolOrgCode());
		} else {
			roleByNameAndOrgId = roleMapper.findRoleByNameAndOrgId(
					role.getRoleName(), role.getOrgId(), null);
		}
		if (ObjectUtils.isNotEmptyList(roleByNameAndOrgId)) {
			// 修改
			if (role.getId() != null) {
				for (SysRole sysRole : roleByNameAndOrgId) {
					if (sysRole.getId().longValue() != role.getId().longValue()) {
						throw new Exception("角色名称重复！");
					}
				}
			}// 新增
			else {
				throw new Exception("角色名称重复！");
			}
		}
		// 角色类别
		if (StringUtils.isEmpty(role.getRoleType())) {
			throw new Exception("请选择角色类别！");
		}
	}

}
