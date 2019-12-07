package com.scaler7.admin.mapper;

import com.scaler7.admin.domain.Permission;

import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author scaler7
 * @since 2019-12-04
 */
public interface PermissionMapper extends BaseMapper<Permission> {
	
	List<Permission> selectPermissionsByUserId(@Param("id") Integer id);
	
}
