package com.scaler7.admin.service;

import com.scaler7.admin.domain.Role;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author scaler7
 * @since 2019-12-04
 */
public interface IRoleService extends IService<Role> {
	
	/**
	 * 
	* @Title: queryRolesByUserId  
	* @Description: 根据用户id，查询用户所拥有的角色集合
	* @param @param userId 用户id
	* @param @return  角色list集合
	* @return List<Role>
	* @throws
	 */
	List<Role> queryRolesByUserId(Integer userId);

	Role addRole(Role role);

	Role updateRole(Role role);

	void dispatchRolePermission(Integer roleId, List<Integer> pid);
	
}
