package com.cloud.common.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 登录用户的shiro 信息
 * @author  Pan jianneng
 * @date  2018/8/2 17:12
 */
@Data
public class ShiroUser implements Serializable {
	public Long id;
	public String loginName;
	public String nickName;
	public String mobilePhone;
	public String status;

	public Long roleId;
	public String roleName;
	private Long orgId;
	private String roleType;
	private String orgType;
	private String orgCode;

	private Long schoolOrgId;
	private String schoolOrgCode;
	private Date loginTime;

	public ShiroUser(Long id, String loginName, String nickName) {
		this.id = id;
		this.loginName = loginName;
		this.nickName = nickName;

	}

	public ShiroUser(Long id, String loginName, String nickName, String status,Long roleId,
					 String roleName,String mobilePhone,Long orgId,String roleType,String orgType,String orgCode,
					 Date loginTime) {
		this.id = id;
		this.loginName = loginName;
		this.nickName = nickName;
		this.status = status;
		this.roleName = roleName;
		this.roleId = roleId;
		this.mobilePhone = mobilePhone;
		this.orgId = orgId;
		this.roleType = roleType;
		this.orgType = orgType;
		this.orgCode = orgCode;
		this.loginTime = loginTime;
	}
}
