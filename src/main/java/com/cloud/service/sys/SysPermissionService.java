package com.cloud.service.sys;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.common.utils.ObjectUtils;
import com.cloud.common.vo.SysPermissionTree;
import com.cloud.entity.sys.SysPermission;
import com.cloud.entity.sys.SysRolePermissionRelation;
import com.cloud.mapper.master.sys.SysPermissionMapper;
import com.cloud.mapper.master.sys.SysRolePermissionRelationMapper;

/**
 * 添加文件描述
 *
 * @author Pan jianneng
 * @time 2018/10/20 14:38
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysPermissionService {

    @Resource
    private SysPermissionMapper permissionMapper;
    @Resource
    private SysRolePermissionRelationMapper relationMapper;

    /**
    * @description: 根据当前登录用户的id获取权限
    * @param:  [userId]  当前登录用户id
    * @return: java.util.List<com.cloud.entity.sys.SysPermission>
    * @author: huangYl
    * @date:   2018/10/30 10:02
    */
    public List<SysPermission> findPermissionNameByUserId(Long userId) {
        List<SysPermission> list = permissionMapper.findPermissionNameByUserId(userId);
        if (null == list || list.size() == 0) {
            return new ArrayList<>();
        }
        return list;
    }

    /**
    * @description:  根据当前登录用户的角色id获取权限
    * @param:  [roleId]  角色id
    * @param:  [roleType] 角色类型
    * @return: java.util.List<com.cloud.entity.sys.SysPermission>
    * @author: huangYl
    * @date:   2018/11/6 19:05
    */
    public List<SysPermission> findPermissionNameByRoleId(Long roleId,String roleType) {
        List<SysPermission> list = permissionMapper.findPermissionNameByRoleId(roleId,roleType);
        if (null == list || list.size() == 0) {
            return new ArrayList<>();
        }
        return list;
    }
    
    /**
	 * 查询角色权限树
	 * @param roleId 角色id
	 * @author yuxin
	 * @date 2018年11月13日 下午4:58:28 
	 * @return
	 */
	public SysPermissionTree getPermissionTree(Long roleId,String roleType){
		//查询该角色的所有权限
		List<SysPermission> rolePermissions = permissionMapper.findPermissionNameByRoleId(roleId,roleType);
		Map<Long,List<SysPermission>> rolePermissionMap = new LinkedHashMap<Long, List<SysPermission>>();
		for(SysPermission rp : rolePermissions){
			List<SysPermission> mapValue = new ArrayList<SysPermission>();
			if(rolePermissionMap.containsKey(rp.getParentId())){
				mapValue = rolePermissionMap.get(rp.getParentId());
				rolePermissionMap.remove(rp.getParentId());
			}
			mapValue.add(rp);
			rolePermissionMap.put(rp.getParentId(), mapValue);
		}
		//默认取第一个
		SysPermissionTree tree = wrapRelationTree(rolePermissions.get(0));
		//构建权限树
		rebulidPermissionTree(rolePermissionMap, tree, tree.getId());
		return tree;
	}

	 /**
	  * 包装成树形结构
	  * @param per 权限信息 
	  * @author yuxin
	  * @date 2018年11月13日 下午3:53:48 
	  * @return
	  */
    private SysPermissionTree wrapRelationTree(SysPermission per) {
    	SysPermissionTree tree = new SysPermissionTree();
        try {
            org.springframework.beans.BeanUtils.copyProperties(per, tree);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return tree;
    }
    
    /**
     * 构建权限树
     * @param rolePermissionMap
     * @param tree
     * @param parentId 0
     * @author yuxin
     * @date 2018年11月13日 下午3:59:43 
     * @return
     */
    private void rebulidPermissionTree(Map<Long,List<SysPermission>> rolePermissionMap,SysPermissionTree tree,Long parentId){
		if(rolePermissionMap.containsKey(parentId)){
			List<SysPermission> rolePermissions = rolePermissionMap.get(parentId);
			List<SysPermissionTree> children = new ArrayList<SysPermissionTree>();
			for(SysPermission rp : rolePermissions){
				SysPermissionTree permissionTree = wrapRelationTree(rp);
				rebulidPermissionTree(rolePermissionMap, permissionTree, rp.getId());
				children.add(permissionTree);
			}
			tree.setChildren(children);
		}
	}
    
    /**
     * 角色权限更新
     * @param ids 要保留的权限ids
     * @param roleId 被修改的角色id
     * @author yuxin
     * @date 2018年11月13日 下午6:04:07 
     * @return
     */
    public void updatePermission(List<Long> ids,Long roleId){
    	//先删除该角色之前的所有权限
        relationMapper.delByRoleId(roleId);
        List<SysRolePermissionRelation> relations = new ArrayList<SysRolePermissionRelation>();
        if(ObjectUtils.isNotEmptyList(ids)){
        	//插入新权限
            for (Long permissionId : ids) {
            	SysRolePermissionRelation perRelation = new SysRolePermissionRelation();
            	perRelation.setRoleId(roleId);
            	perRelation.setPermissionId(permissionId);
            	relations.add(perRelation);
    		}
            relationMapper.batchInsert(relations);
        }
    }
    
}
