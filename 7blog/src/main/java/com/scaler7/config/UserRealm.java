package com.scaler7.config;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import com.scaler7.admin.domain.Permission;
import com.scaler7.admin.domain.Role;
import com.scaler7.admin.domain.User;
import com.scaler7.admin.service.IPermissionService;
import com.scaler7.admin.service.IRoleService;
import com.scaler7.admin.service.IUserService;
import com.scaler7.common.ActiveUser;
import com.scaler7.common.Constant;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	IUserService userService;
	@Autowired
	IRoleService roleService;
	@Autowired
	IPermissionService permissionService;

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String name = token.getPrincipal().toString();
		// 构造查询条件
		User queryUser = new User();
		queryUser.setName(name);
		// 根据名称查询用户是否存在
		User user = userService.getOneUser(queryUser);
		ActiveUser activeUser = new ActiveUser();
		activeUser.setUser(user);
		if(null != user) {
			List<Role> roles = roleService.queryRolesByUserId(user.getId());
			List<Permission> permissions = permissionService.queryPermissionsByUserId(user.getId());
			activeUser.setRole(roles);
			activeUser.setPermission(permissions);
		}
		AuthenticationInfo info = new SimpleAuthenticationInfo(activeUser, user.getPassword(), this.getName());
		return info;
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		ActiveUser activeUser = (ActiveUser) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		List<Role> roles = activeUser.getRole();
		List<Permission> permissions = activeUser.getPermission();
		if(null != permissions) {
			for (Permission permission : permissions) {
				if(permission.getState() == Constant.STATE_AVAILABLE_TRUE) {
					info.addStringPermission(permission.getName());
				}
			}
		}
		
		if(null !=roles) {
			for (Role role : roles) {
				if(role.getState() == Constant.STATE_AVAILABLE_TRUE) {
					info.addRole(role.getName());
				}
			}
		}
		return info;

	}

}
