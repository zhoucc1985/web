package com.cloud.shiro;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.cloud.common.dict.CommonDict;
import com.cloud.common.utils.ObjectUtils;
import com.cloud.common.vo.ShiroUser;
import com.cloud.common.vo.sys.org.OrgDetailVo;
import com.cloud.entity.sys.SysPermission;
import com.cloud.entity.sys.SysRole;
import com.cloud.entity.sys.SysUser;
import com.cloud.service.sys.OrgInfoService;
import com.cloud.service.sys.SysPermissionService;
import com.cloud.service.sys.SysRoleService;
import com.cloud.service.sys.SysUserService;

/**
 * 添加文件描述
 *
 * @author Pan jianneng
 * @time 2018/10/19 14:40
 */
public class ShiroRealm extends AuthorizingRealm {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    /**
     * 如果项目中用到了事物，@Autowired注解会使事物失效，可以自己用get方法获取值
     */
    @Autowired
    @Lazy
    private SysRoleService roleService;

    @Autowired
    @Lazy
    private SysUserService userService;

    @Autowired
    @Lazy
    private SysPermissionService permissionService;

    @Autowired
    @Lazy
    private OrgInfoService orgInfoService;

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        logger.info("---------------- 执行 Shiro 凭证认证 ----------------------");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        SysUser loginUser = new SysUser();
        loginUser.setLoginName(name);
        loginUser.setLoginPwd(password);
        // 从数据库获取对应用户名密码的用户
        SysUser user = userService.findByUsername(name);
        //用户锁定状态
        String userStatus = "LOCKING";
        if (user != null) {
            // 用户为禁用状态
            if (userStatus.equals(user.getStatus())) {
                throw new DisabledAccountException();
            }
            logger.info("---------------- Shiro 凭证认证成功 ----------------------");
            ShiroUser shiroUser = new ShiroUser(user.getId(),user.getLoginName(),
                    user.getNickName(),user.getStatus(),user.getRoleId(),
                    user.getRoleName(),user.getMobilePhone(),user.getOrgId(),user.getRoleType(),
                    user.getOrgType(),user.getOrgCode(),user.getLoginTime());

            setSysUserSchoolOrgInfo(shiroUser);
            //参数说明 用户、密码、盐值、realm name
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    shiroUser,
                    user.getLoginPwd(),
                    ByteSource.Util.bytes(user.getSalt()),
                    getName()
            );
            //设置登录时间
            user.setLoginTime(new Date());
			userService.setLoginTime(user);
            return authenticationInfo;
        }
        if (ObjectUtils.isNullObject(user)) {
            logger.error("登录用户不存在" + loginUser.getLoginName());
        }
        throw new UnknownAccountException();
    }

	/**
	 * 设置 当用户 学校组织机构信息。如果当前用户所属组织机构 为学校上级 则设置为空
     */
    private void setSysUserSchoolOrgInfo(ShiroUser shiroUser) {
        if(CommonDict.ORG_TYPE_DEPARTMENT.equals(shiroUser.getOrgType())){
            OrgDetailVo currentAndParentOrg = orgInfoService.getCurrentAndParentOrgDepart(shiroUser.getOrgId());
            shiroUser.setSchoolOrgId(Long.valueOf(currentAndParentOrg.getParentId()));
            shiroUser.setSchoolOrgCode(currentAndParentOrg.getParentCode());
        }
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("---------------- 执行 Shiro 权限获取 ---------------------");
        Object principal = principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (principal instanceof ShiroUser) {
            ShiroUser userLogin = (ShiroUser) principal;
            //查询角色并授权
            SysRole sysRole = roleService.selectByUserId(userLogin.getId());
            if (sysRole != null) {
                authorizationInfo.addRole(sysRole.getRoleType());
            }
            //查询权限并授权
           List<SysPermission> permissions = permissionService.findPermissionNameByRoleId(userLogin.getRoleId(),
        		   userLogin.getRoleType());
            Set<String> permissionsSet = permissions.parallelStream().map(p -> p.getName()).distinct().collect(Collectors.toSet());
            authorizationInfo.addStringPermissions(permissionsSet);
        }
        logger.info("---- 获取到以下角色 ----");
        logger.info(authorizationInfo.getRoles().toString());
        logger.info("---- 获取到以下权限 ----");
        logger.info(authorizationInfo.getStringPermissions().toString());
        logger.info("---------------- Shiro 权限获取成功 ----------------------");
        return authorizationInfo;
    }

}
