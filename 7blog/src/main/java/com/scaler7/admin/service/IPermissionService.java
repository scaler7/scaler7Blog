package com.scaler7.admin.service;

import com.scaler7.admin.domain.Permission;
import com.scaler7.admin.vo.PermissionVO;

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
public interface IPermissionService extends IService<Permission> {
	
	/**
	 * 
	* @Title: queryPermissionsByUserId  
	* @Description: 根据用户id，查询所拥有的权限
	* @param @param userId 用户id
	* @param @return  权限list集合
	* @return List<Permission>
	* @throws
	 */
	List<Permission> queryPermissionsByUserId(Integer userId);

	List<Permission> queryAllPermissionForList(PermissionVO permissionVO);

	Permission addPermission(Permission permission);

	Permission updatePermission(Permission permission);

}
