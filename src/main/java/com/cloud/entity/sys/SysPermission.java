package com.cloud.entity.sys;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 请添加描述
* @author yuxin
* @time 2018/11/13
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysPermission implements Serializable {
    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private Long id;

    /**
    * 权限名
    */
    @ApiModelProperty(notes = "权限名")
    private String name;

    /**
    * 父节点id
    */
    @ApiModelProperty(notes = "父节点id")
    private Long parentId;

    /**
    * 描述
    */
    @ApiModelProperty(notes = "描述")
    private String descreption;

    /**
    * 权限类型 1:系统管理 2：业务管理
    */
    @ApiModelProperty(notes = "权限类型 1:系统管理 2：业务管理")
    private Long permissionType;

    /**
    * 权限状态  NORMAL：正常
    */
    @ApiModelProperty(notes = "权限状态  NORMAL：正常")
    private String status;
    
    /**
     * 权限是否勾选 true:已选    false:未选
     */
    private Boolean selected = false;

    /**
     * 角色权限关联id
     * */
    private Long relationId;

    /**
    * 权限code
    */
    @ApiModelProperty(notes = "权限code")
    private String code;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", parentId=").append(parentId);
        sb.append(", descreption=").append(descreption);
        sb.append(", permissionType=").append(permissionType);
        sb.append(", status=").append(status);
        sb.append(", code=").append(code);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    
    public Boolean getSelected(){
    	if(relationId!=null){
    		selected=true;
    	}
    	return selected;
    }
    
    public void setSelected(Boolean selected){
    	this.selected=selected;
    }
}