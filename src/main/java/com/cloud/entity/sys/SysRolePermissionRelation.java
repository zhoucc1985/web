package com.cloud.entity.sys;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 角色权限关联类
* @author yuxin
* @time 2018/11/06
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRolePermissionRelation implements Serializable {
    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private Long id;

    /**
    * 角色id
    */
    @ApiModelProperty(notes = "角色id")
    private Long roleId;

    /**
    * 权限id
    */
    @ApiModelProperty(notes = "权限id")
    private Long permissionId;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleId=").append(roleId);
        sb.append(", permissionId=").append(permissionId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}