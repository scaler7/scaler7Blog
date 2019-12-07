package com.scaler7.admin.service.impl;

import com.scaler7.admin.domain.Role;
import com.scaler7.admin.mapper.RoleMapper;
import com.scaler7.admin.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

	@Autowired
	RoleMapper roleMapper;
	
	@Override
	public List<Role> queryRolesByUserId(Integer userId) {
		List<Role> roles = roleMapper.selectRolesByUserId(userId);
		return roles;
	}

	@Override
	public Role addRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role updateRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dispatchRolePermission(Integer roleId, List<Integer> pid) {
		// TODO Auto-generated method stub
		
	}

}
