package com.cloud.common.vo;

import java.util.List;

/** 
 * @author yuxin
 * @version 创建时间：2018年11月15日 下午9:23:29 
 * 类说明 
 */
public class UserVo {
	/**
	 * 用户ids
	 */
	private List<Long> userIds;
	/**
	 * 用户密码
	 */
	private String pwd;
	public List<Long> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	

}
