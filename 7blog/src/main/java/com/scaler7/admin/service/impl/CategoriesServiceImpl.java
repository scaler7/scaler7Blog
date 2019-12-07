package com.scaler7.admin.service.impl;

import com.scaler7.admin.domain.Categories;
import com.scaler7.admin.mapper.CategoriesMapper;
import com.scaler7.admin.service.ICategoriesService;
import com.scaler7.admin.vo.CategoriesVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author scaler7
 * @since 2019-11-29
 */
@Service
public class CategoriesServiceImpl extends ServiceImpl<CategoriesMapper, Categories> implements ICategoriesService {

	@Autowired
	CategoriesMapper categoriesMapper;

	@Override
	public List<Categories> getCategoriesList(CategoriesVO categoriesVO) {
		QueryWrapper<Categories> wrapper = new QueryWrapper<Categories>();
		if (null != categoriesVO) {

		}
		List<Categories> categoriesList = categoriesMapper.selectList(wrapper);
		return categoriesList;
	}

	public Integer addToCategoriesCount(String name) {
		return categoriesMapper.increaseCategoriesCount(name);
	}

	public Integer cutDownCategoriesCount(String name) {
		return categoriesMapper.decreaseCategoriesCount(name);
	}

	@Override
	@CacheEvict(cacheNames = "com.scaler7.admin.service.impl.CategoriesServiceImpl",key="#id")
	public void removeCategory(Integer id) {
		categoriesMapper.deleteById(id);
	}

	@Override
	@CachePut(cacheNames = "com.scaler7.admin.service.impl.CategoriesServiceImpl",key="#categories.id")
	public Categories saveCategory(Categories categories) {
		categoriesMapper.insert(categories);
		return categories;
	}

	@Override
	@Cacheable(cacheNames = "com.scaler7.admin.service.impl.CategoriesServiceImpl",key = "#id")
	public Categories getOneCategory(Integer id) {
		Categories categories = categoriesMapper.selectById(id);
		return categories;
	}

}
