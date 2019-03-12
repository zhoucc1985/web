package com.cloud.entity.sys;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.cloud.common.dict.CommonDict;
import com.cloud.common.utils.UserUtils;
import com.cloud.common.vo.ShiroUser;

/**
* 用户
* @author yuxin
* @time 2018/11/12
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUser implements Serializable {
    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private Long id;

    /**
    * 用户名
    */
    @ApiModelProperty(notes = "用户名")
    private String loginName;

    /**
    * 密码
    */
    @ApiModelProperty(notes = "密码")
    private String loginPwd;

    /**
    * 姓名
    */
    @ApiModelProperty(notes = "姓名")
    private String nickName;

    /**
    * 工号
    */
    @ApiModelProperty(notes = "工号")
    @Pattern(regexp = "^[0-9]*$",message="工号应为数字")
    private String jobNumber;

    /**
    * 手机号码
    */
    @ApiModelProperty(notes = "手机号码")
    private String mobilePhone;

    /**
    * 性别（1:男  2:女）
    */
    @ApiModelProperty(notes = "性别（1:男  2:女）")
    private Integer sex;

    /**
    * 用户状态 （INITIAL:初始，NORMAL:正常，LOCKING:锁定）
    */
    @ApiModelProperty(notes = "用户状态 （INITIAL:初始，NORMAL:正常，LOCKING:锁定）")
    private String status;

    /**
    * 角色id
    */
    @ApiModelProperty(notes = "角色id")
    private Long roleId;

    /**
    * 组织机构id
    */
    @ApiModelProperty(notes = "组织机构id")
    private Long orgId;

    /**
    * 密码盐
    */
    @ApiModelProperty(notes = "密码盐")
    private String salt;

    /**
    * 是否已删除 0:未删除  1:已删除
    */
    @ApiModelProperty(notes = "是否已删除 0:未删除  1:已删除")
    private Boolean isDelete;

    /**
    * 登录时间
    */
    @ApiModelProperty(notes = "登录时间")
    private Date loginTime;

    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 所属机构名称
     */
    private String orgName;
    /**
     * 角色类型 
     */
    private String roleType;
    /**
     * 机构类型
     */
    private String orgType;
    /**
     * 机构编码
     */
    private String orgCode;
    /**
     * 机构号
     */
    private String orgNumber;
    
    /**
     * 是否为组织默认管理员
     */
    private Boolean defaultAdmin;
    
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", loginName=").append(loginName);
        sb.append(", loginPwd=").append(loginPwd);
        sb.append(", nickName=").append(nickName);
        sb.append(", jobNumber=").append(jobNumber);
        sb.append(", mobilePhone=").append(mobilePhone);
        sb.append(", sex=").append(sex);
        sb.append(", status=").append(status);
        sb.append(", roleId=").append(roleId);
        sb.append(", roleName=").append(roleName);
        sb.append(", orgId=").append(orgId);
        sb.append(", salt=").append(salt);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", defaultAdmin=").append(defaultAdmin);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * 用户状态枚举
     * @author yuxin
     *
     */
    public enum UserStateEnum {
        /**
         * 用户初始状态
         */
    	INITIAL_STATUS("INITIAL", "初始状态"),
    	/**
         * 用户正常状态
         */
    	NORMAL_STATUS("NORMAL", "正常状态"),
    	/**
         * 用户锁定状态
         */
    	LOCKING_STATUS("LOCKING", "锁定状态");
		private String code;
		private String name;

		UserStateEnum(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}
}