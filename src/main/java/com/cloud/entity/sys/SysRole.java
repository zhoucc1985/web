package com.cloud.entity.sys;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 角色
* @author yuxin
* @time 2018/11/06
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRole implements Serializable {
    /**
    * 角色id
    */
    @ApiModelProperty(notes = "")
    private Long id;

    /**
    * 角色名称
    */
    @ApiModelProperty(notes = "角色名称")
    private String roleName;

    /**
    * 角色类型 ADMIN:管理员 ordinary:普通用户
    */
    @ApiModelProperty(notes = "角色类型 ADMIN:管理员   ORDINARY:普通用户")
    private String roleType;

    /**
    * 组织机构id
    */
    @ApiModelProperty(notes = "组织机构id")
    private Long orgId;

    /**
    * 备注
    */
    @ApiModelProperty(notes = "备注")
    private String remarks;
    
    /**
     * 是否为组织默认角色
     */
    @ApiModelProperty(notes = "是否为组织默认角色")
    private Boolean defaultRole=false;
    /**
     * 组织机构code
     */
    private String orgCode;
    /**
     * 组织机构名称
     */
    private String orgName;
    /**
     * 组织机构类型
     */
    private String orgType;

    private static final long serialVersionUID = 1L;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleName=").append(roleName);
        sb.append(", roleType=").append(roleType);
        sb.append(", orgId=").append(orgId);
        sb.append(", remarks=").append(remarks);
        sb.append(", defaultRole=").append(defaultRole);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * 用户状态枚举
     * @author yuxin
     *
     */
    public enum RoleTypeEnum {
        /**
         * 用户初始状态
         */
    	ADMIN_ROLE("ADMIN", "管理员角色"),
    	/**
         * 用户正常状态
         */
    	ORDINARY_ROLE("ORDINARY", "普通用户角色");
    	
		private String code;
		private String name;

		RoleTypeEnum(String code, String name) {
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