package com.scaler7.admin.service;

import com.scaler7.admin.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author scaler7
 * @since 2019-12-04
 */
public interface IUserService extends IService<User> {
	
	User getOneUser(User user);
	
}
