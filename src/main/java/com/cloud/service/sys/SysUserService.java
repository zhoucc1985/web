package com.cloud.service.sys;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.cloud.common.dict.CommonDict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cloud.common.utils.ObjectUtils;
import com.cloud.common.utils.UserUtils;
import com.cloud.common.vo.ShiroUser;
import com.cloud.common.vo.UserVo;
import com.cloud.entity.sys.SysPermission;
import com.cloud.entity.sys.SysRole;
import com.cloud.entity.sys.SysRolePermissionRelation;
import com.cloud.entity.sys.SysUser;
import com.cloud.entity.sys.SysUser.UserStateEnum;
import com.cloud.mapper.master.sys.OrgInfoMapper;
import com.cloud.mapper.master.sys.SysPermissionMapper;
import com.cloud.mapper.master.sys.SysRoleMapper;
import com.cloud.mapper.master.sys.SysRolePermissionRelationMapper;
import com.cloud.mapper.master.sys.SysUserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 添加文件描述
 *
 * @author Pan jianneng
 * @time 2018/10/19 14:46
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserService {
	private static final Logger logger = LoggerFactory
			.getLogger(SysUserService.class);

	@Resource
	private SysUserMapper userMapper;
	@Resource
	private SysRoleMapper roleMapper;
	@Resource
	private SysRolePermissionRelationMapper relationMapper;
	@Resource
	private SysPermissionMapper permissionMapper;
	@Resource
	private OrgInfoMapper orgInfoMapper;
	
	public SysUser findByUsername(String username) {
		SysUser dbu = null;
		try {
			dbu = userMapper.findByUsername(username);
		} catch (Exception e) {
			logger.error("根据登录用户查找用户失败：{}", e.getMessage());
			// throw new ServiceException("获取用户信息失败！");
		}
		return dbu;
	}

	/**
	 * 根据用户ID 查询 用户信息
	 * @param id 用户id
	 * @author yuxin
	 * @return 用户信息
	 */
	public SysUser findUserById(Long id) {
		if(ObjectUtils.isEmptyObject(id)){
			ShiroUser currentlyUserInfo = UserUtils.getInstance().getCurrentlyUserInfo();
			id=currentlyUserInfo.getId();
		}
		return userMapper.selectByPrimaryKey(id);
	}


	/**
	 * 查询该用户下是否有组织机构
	 * 
	 * @param orgId
	 *            组织机构id
	 * @return com.cloud.entity.sys.SysUser
	 * @author huangYl
	 */
	public SysUser selectUserByOrgId(Long orgId) {
		SysUser user = null;
		try {
			user = userMapper.selectUserByOrgId(orgId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据组织机构id查询用户失败：{} id = " + orgId, e.getMessage());
		}
		return user;
	}

	/**
	 * 查询用户列表
	 * 
	 * @param user
	 *            用户信息
	 * @param pageNum
	 * @param pageSize
	 * @author yuxin
	 * @date 2018年11月15日 下午3:45:56
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public PageInfo<SysUser> findUserList(SysUser user, int pageNum,
			int pageSize) {
		List<SysUser> userList = null;
		String orgCode="";
		// 没有选择机构  默认当前登录用户的
		if (ObjectUtils.isEmptyObject(user.getOrgId())) {
			ShiroUser currentlyUserInfo = UserUtils.getInstance()
					.getCurrentlyUserInfo();
			orgCode=currentlyUserInfo.getOrgCode();
		} else {
			orgCode=user.getOrgCode();
		}
		PageHelper.startPage(pageNum, pageSize);
		userList = userMapper.findUserList(user.getLoginName(),
				orgCode, user.getRoleId(), user.getStatus());
		// 设置defaultAdmin的值
		for(SysUser sysUser:userList){
			String loginName=sysUser.getLoginName();
			String orgNumber=sysUser.getOrgNumber();
			if(CommonDict.SUPER_ADMIN_LOGINNAME.equals(sysUser.getLoginName())||loginName.equals(CommonDict.SUPER_ADMIN_LOGINNAME + orgNumber)){
				sysUser.setDefaultAdmin(true);
			}else{
				sysUser.setDefaultAdmin(false);
			}
		}
		return new PageInfo<SysUser>(userList);
	}
	
	/**
	 * 新增用户
	 * 
	 * @param user
	 *            用户信息
	 * @author yuxin
	 * @date 2018年11月14日 下午6:20:14
	 * @return
	 */
	public void addUser(SysUser user) throws Exception {
		// 验证用户信息
		validate(user);
		String salt = UserUtils.getGenerateSaltStr();
		// 密码加密
		String loginPwd = UserUtils.getUserEncryptPassWord(user.getLoginPwd(),
				salt);
		user.setLoginPwd(loginPwd);
		user.setSalt(salt);
		userMapper.insertSelective(user);
	}

	/**
	 * 更新用户
	 * 
	 * @param user
	 *            用户信息
	 * @author yuxin
	 * @date 2018年11月15日 下午2:27:13
	 * @return
	 */
	public void updateUser(SysUser user) throws Exception {
		 if(ObjectUtils.isEmptyObject(user.getId())){
			 ShiroUser currentlyUserInfo =
					 UserUtils.getInstance().getCurrentlyUserInfo();
			 user.setId(currentlyUserInfo.getId());
		 }
		validate(user);
		userMapper.updateByPrimaryKeySelective(user);
	}

	/**
	 * 用户验证
	 * 
	 * @param user
	 *            用户信息
	 * @author yuxin
	 * @date 2018年11月14日 下午6:02:39
	 * @return
	 */
	public void validate(SysUser user) throws Exception {
		// 新增时
		if (ObjectUtils.isEmptyObject(user.getId())) {
			if (StringUtils.isEmpty(user.getLoginName())) {
				throw new Exception("用户名不能为空！");
			}
			if (StringUtils.isEmpty(user.getLoginPwd())) {
				throw new Exception("密码不能为空！");
			}// 账户名唯一性
			List<SysUser> checkLoginName = userMapper.checkLoginName(user
					.getLoginName());
			if (ObjectUtils.isNotEmptyList(checkLoginName)) {
				throw new Exception("该用户名称已存在！");
			}
		}// 非空验证
		if (StringUtils.isEmpty(user.getMobilePhone())) {
			throw new Exception("手机号不能为空！");
		}
		if (ObjectUtils.isEmptyObject(user.getRoleId())) {
			throw new Exception("用户角色不能为空！");
		}
		if (ObjectUtils.isEmptyObject(user.getOrgId())) {
			throw new Exception("所属机构不能为空！");
		}
		if (StringUtils.isEmpty(user.getStatus())) {
			throw new Exception("用户状态不能为空！");
		}
	}

	/**
	 * 删除用户
	 * 
	 * @param ids 删除用户的编号组
	 *            用户id
	 * @author yuxin
	 * @date 2018年11月15日 下午3:59:36
	 * @return
	 */
	public void delUserById(List<Long> ids) {
		for (Long id : ids) {
			userMapper.deleteByPrimaryKey(id);
		}
	}

	/**
	 * 修改用户密码
	 * 
	 * @param loginPwd
	 *            新密码
	 * @author yuxin
	 * @date 2018年11月15日 下午8:29:23
	 * @return
	 * @throws Exception 
	 */
	public void changePwd(String oldLoginPwd,String loginPwd) throws Exception {
		ShiroUser currentlyUserInfo = UserUtils.getInstance()
				.getCurrentlyUserInfo();
		//验证旧密码  获取数据库密码盐
		SysUser userInfo = userMapper.selectInfoById(currentlyUserInfo.getId());
		//判断用户状态  初始状态不用旧密码
		if(!UserStateEnum.INITIAL_STATUS.getCode().equals(userInfo.getStatus())){
			// 密码加密
			String checkLoginPwd = UserUtils.getUserEncryptPassWord(oldLoginPwd,
					userInfo.getSalt());
			if(!checkLoginPwd.equals(userInfo.getLoginPwd())){
				throw new Exception("原密码错误！");
			}
		}
		SysUser user = new SysUser();
		user.setId(currentlyUserInfo.getId());
		String salt = UserUtils.getGenerateSaltStr();
		// 密码加密
		String newLoginPwd = UserUtils.getUserEncryptPassWord(loginPwd, salt);
		user.setLoginPwd(newLoginPwd);
		user.setSalt(salt);
		user.setStatus("NORMAL");
		userMapper.updateByPrimaryKeySelective(user);
	}

	/**
	 * 初始化用户密码
	 * 
	 * @param
	 * @author yuxin
	 * @date 2018年11月15日 下午9:29:47
	 * @return
	 */
	public void initPwd(UserVo vo) {
		for (Long id : vo.getUserIds()) {
			SysUser sysUser = new SysUser();
			// 设置状态为初始
			sysUser.setStatus(UserStateEnum.INITIAL_STATUS.getCode());
			sysUser.setId(id);
			String salt = UserUtils.getGenerateSaltStr();
			// 密码加密
			String loginPwd = UserUtils.getUserEncryptPassWord(vo.getPwd(),
					salt);
			sysUser.setLoginPwd(loginPwd);
			sysUser.setSalt(salt);
			userMapper.updateByPrimaryKeySelective(sysUser);
		}
	}

	/**
	 * 添加默认角色和管理员
	 * 
	 * @param number
	 *            组织机构号
	 * @param orgId
	 *            组织机构id
	 * @author yuxin
	 * @date 2018年11月16日 上午10:59:38
	 * @return
	 */
	public void addDefaultRoleAndAdmin(String number, Long orgId,String orgName) {
		// 添加默认角色
		SysRole sysRole = new SysRole();
		sysRole.setRoleName("系统管理员");
		sysRole.setOrgId(orgId);
		sysRole.setRoleType("ADMIN");
		sysRole.setDefaultRole(true);
		roleMapper.insertSelective(sysRole);
		// 添加默认管理员
		SysUser sysUser = new SysUser();
		sysUser.setLoginName("admin" + number);
		sysUser.setNickName("admin");
		sysUser.setRoleId(sysRole.getId());
		sysUser.setOrgId(orgId);
		sysUser.setSex(1);
		String salt = UserUtils.getGenerateSaltStr();
		// 密码加密
		String newLoginPwd = UserUtils.getUserEncryptPassWord("000000", salt);
		sysUser.setLoginPwd(newLoginPwd);
		sysUser.setSalt(salt);
		userMapper.insertSelective(sysUser);
		// 添加角色权限
		List<SysRolePermissionRelation> relationList = new ArrayList<SysRolePermissionRelation>();
		List<SysPermission> findAll = permissionMapper.findAll();
		for (SysPermission sysPermission : findAll) {
			SysRolePermissionRelation relation = new SysRolePermissionRelation();
			relation.setPermissionId(sysPermission.getId());
			relation.setRoleId(sysRole.getId());
			relationList.add(relation);
		}
		relationMapper.batchInsert(relationList);
	}
	
	/**
	 * 设置用户登录时间
	 * @param
	 * @author yuxin
	 * @date 2018年12月17日 下午2:13:20 
	 * @return
	 */
	public void setLoginTime(SysUser user){
		userMapper.updateByPrimaryKeySelective(user);
	}
}
