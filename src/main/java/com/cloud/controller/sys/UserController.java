package com.cloud.controller.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.common.utils.CommonUtils;
import com.cloud.common.vo.UserVo;
import com.cloud.entity.sys.SysUser;
import com.cloud.service.sys.SysUserService;
import com.github.pagehelper.PageInfo;

/** 
 * @author yuxin
 * @version 创建时间：2018年11月14日 下午4:42:06 
 * 类说明  用户
 */
@RestController
@RequestMapping(value="/api/user")
@Api(tags = "用户", description = "用户管理接口")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	private SysUserService userService;
	
	/**
	 * 查询用户列表
	 * @param user 查询的用户信息
	 * @param pageNum 
	 * @param pageSize 
	 * @author yuxin
	 * @date 2018年11月14日 下午5:33:31 
	 * @return
	 */
	@ApiOperation(value = " 查询用户列表")
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public Map<String, Object> findUserList(@RequestBody SysUser user,
			@RequestParam(value="pageNum")int pageNum,
			@RequestParam(value="pageSize")int pageSize){
		try {
			PageInfo<SysUser> findUserList = userService.findUserList(user,pageNum,pageSize);
			return CommonUtils.getSucResultMap(findUserList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询用户列表异常");
			return CommonUtils.getErrorResultMap("查询用户列表异常");
		}
	}

	/**
	 * 根据用户id 查询用户信息
	 * @param id  用户id
	 * @author yuxin
	 * @return  用户信息
	 */
	@ApiOperation(value = "根据用户id 查询用户信息")
	@RequestMapping(value = "/info",method = RequestMethod.GET)
	public Map<String, Object> findInfo(@RequestParam(value="id",required=false)Long id) {
		try {
			SysUser sysUser = userService.findUserById(id);
			return CommonUtils.getSucResultMap(sysUser);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("查询用户信息异常");
            return CommonUtils.getErrorResultMap("查询用户信息异常");
		}
	}
	
	/**
	 * 新增用户
	 * @param user 用户信息
	 * @author yuxin
	 * @date 2018年11月14日 下午5:41:33 
	 * @return
	 */
	@ApiOperation(value = "新增用户")
	@RequestMapping(value="",method=RequestMethod.POST)
	public Map<String,Object> addUser(@RequestBody @Valid SysUser user){
		try {
			userService.addUser(user);
			return CommonUtils.getSucResultMap("新增用户成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增用户异常");
			return CommonUtils.getErrorResultMap("新增用户异常:"+e.getLocalizedMessage());
		}
	}
	
	/**
	 * 更新用户信息
	 * @param sysUser 用户信息
	 * @author yuxin
	 * @date 2018年11月15日 下午3:54:08 
	 * @return
	 */
	@ApiOperation(value = "更新用户信息")
	@RequestMapping(value="",method=RequestMethod.PUT)
	public Map<String, Object> updateUser(@RequestBody @Valid SysUser sysUser){
		try {
			userService.updateUser(sysUser);
			return CommonUtils.getSucResultMap("更新用户成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新用户信息异常");
			return CommonUtils.getErrorResultMap("更新用户信息异常:"+e.getLocalizedMessage());
		}
	}
	
	/**
	 * 删除用户
	 * @param userIds 用户ids
	 * @author yuxin
	 * @date 2018年11月15日 下午4:00:04 
	 * @return
	 */
	@ApiOperation(value = "删除用户")
	@RequestMapping(value="/del",method=RequestMethod.DELETE)
	public Map<String, Object> deleteUser(@RequestBody List<Long> userIds){
		try {
			userService.delUserById(userIds);
			return CommonUtils.getSucResultMap("删除用户成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除用户异常");
			return CommonUtils.getErrorResultMap("删除用户异常");
		}
	}
	
	/**
	 * 更改密码
	 * @param loginPwd 密码
	 * @author yuxin
	 * @date 2018年11月15日 下午8:32:06 
	 * @return
	 */
	@ApiOperation(value = "更改密码")
	@RequestMapping(value="/loginPwd",method=RequestMethod.PUT)
	public Map<String,Object> changePwd(@RequestParam(value="oldLoginPwd",required=false)String oldLoginPwd,
			@RequestParam(value="loginPwd")String loginPwd){
		try {
			userService.changePwd(oldLoginPwd,loginPwd);
			Map<String, Object> sucResultMap = CommonUtils.getSucResultMap("修改密码成功");
			sucResultMap.put("userStatus", "NORMAL");
			return sucResultMap;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改密码异常");
			return CommonUtils.getErrorResultMap("修改密码异常:"+e.getLocalizedMessage());
		}
	}
	
	/**
	 * 批量初始化用户密码
	 * @param  user 用户信息
	 * @author yuxin
	 * @date 2018年11月15日 下午9:26:15 
	 * @return
	 */
	@ApiOperation(value = "批量初始化用户密码")
	@RequestMapping(value="/initialize",method=RequestMethod.PUT)
	public Map<String, Object> initPwd(@RequestBody UserVo user){
		try {
			userService.initPwd(user);
			return CommonUtils.getSucResultMap("初始化密码成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("初始化密码异常");
			return CommonUtils.getErrorResultMap("初始化密码异常");
		}
	}
	
}
