package com.cloud.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.cloud.entity.sys.SysPermission;

/** 
 * @author yuxin
 * @version 创建时间：2018年11月13日 下午2:47:59 
 * 类说明  权限树
 */
public class SysPermissionTree extends SysPermission{
	
	private List<SysPermissionTree> children=new ArrayList<SysPermissionTree>();
	
	public List<SysPermissionTree> getChildren(){
		return children;
	}
	
	public void setChildren(List<SysPermissionTree> children){
		this.children=children;
	}

}
