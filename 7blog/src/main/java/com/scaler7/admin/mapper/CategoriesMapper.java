package com.scaler7.admin.mapper;

import com.scaler7.admin.domain.Categories;

import io.lettuce.core.dynamic.annotation.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author scaler7
 * @since 2019-11-29
 */
public interface CategoriesMapper extends BaseMapper<Categories> {
	
	public Integer increaseCategoriesCount(@Param("name") String name);
	
	public Integer decreaseCategoriesCount(@Param("name") String name);
	
}
