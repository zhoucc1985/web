package com.cloud.shiro.cloud;

import java.io.Serializable;

/**
 * 登录用户的shiro 信息
 * 
 * @author xiaoqiang
 * 
 */
public class ShiroUser implements Serializable {
	private static final long serialVersionUID = -1373760761780840081L;
	public Long id;
	public String loginName;
	public String name;

	public Long roleId;
	public String roleCode;
	public Long collegeId;
	public String collegeName;

	public ShiroUser(Long id, String loginName, String name) {
		this.id = id;
		this.loginName = loginName;
		this.name = name;

	}

	public ShiroUser(Long id, String loginName, String name, Long roleId,
                     String roleCode) {
		this.id = id;
		this.loginName = loginName;
		this.name = name;
		this.roleCode = roleCode;
		this.roleId = roleId;
	}
	
	public ShiroUser(Long id, String loginName, String name, Long roleId,
                     String roleCode, Long collegeId, String collegeName) {
		this.id = id;
		this.loginName = loginName;
		this.name = name;
		this.roleCode = roleCode;
		this.roleId = roleId;
		this.collegeId = collegeId;
		this.collegeName = collegeName;
	}

	public ShiroUser(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	@Override
	public String toString() {
		return loginName;
	}

	/**
	 * 重载hashCode,只计算loginName;
	 */
	@Override
	public int hashCode() {
		return loginName.hashCode();
	}

	/**
	 * 重载equals,只计算loginName;
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ShiroUser other = (ShiroUser) obj;
		if (loginName == null) {
			if (other.loginName != null) {
				return false;
			}
		} else if (!loginName.equals(other.loginName)) {
			return false;
		}
		return true;
	}

	public Long getRoleId() {
		return roleId;
	}

	public String getCollegeName() {
		return collegeName != null ? collegeName : "无";
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

}
