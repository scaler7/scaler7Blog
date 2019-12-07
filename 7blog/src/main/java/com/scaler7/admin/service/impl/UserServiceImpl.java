package com.scaler7.admin.service.impl;

import com.scaler7.admin.domain.User;
import com.scaler7.admin.mapper.UserMapper;
import com.scaler7.admin.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.apache.commons.lang3.StringUtils;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public User getOneUser(User user) {
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		if(null != user) {
			wrapper.eq(StringUtils.isNotEmpty(user.getName()), "name", user.getName());
		}
		user = userMapper.selectOne(wrapper);
		return user;
	}

}
