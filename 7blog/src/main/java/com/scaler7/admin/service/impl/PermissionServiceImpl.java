package com.scaler7.admin.service.impl;

import com.scaler7.admin.domain.Permission;
import com.scaler7.admin.mapper.PermissionMapper;
import com.scaler7.admin.service.IPermissionService;
import com.scaler7.admin.vo.PermissionVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author scaler7
 * @since 2019-12-04
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

	@Autowired
	PermissionMapper permissionMapper;
	
	@Override
	public List<Permission> queryPermissionsByUserId(Integer userId) {
		List<Permission> permissions = permissionMapper.selectPermissionsByUserId(userId);
		return permissions;
	}

	@Override
	public List<Permission> queryAllPermissionForList(PermissionVO permissionVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Permission addPermission(Permission permission) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Permission updatePermission(Permission permission) {
		// TODO Auto-generated method stub
		return null;
	}

}
