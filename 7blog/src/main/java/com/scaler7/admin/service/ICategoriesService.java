package com.scaler7.admin.service;

import com.scaler7.admin.domain.Categories;
import com.scaler7.admin.vo.CategoriesVO;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 
* @ClassName: scaler7
* @Description: 分类模块接口 
* @author Zzz  
* @date 2019年12月4日  
*
 */
public interface ICategoriesService extends IService<Categories> {
	/**
	 * 
	* @Title: getCategoriesList  
	* @Description: 根据传入的 CategoriesVO组合查询条件，全查询符合条件的categories
	* @param @param metasVO
	* @param @return  
	* @return List<Categories>
	* @throws
	 */
	List<Categories> getCategoriesList(CategoriesVO metasVO);
	/**
	 * 
	* @Title: addToCategoriesCount  
	* @Description: 根据name更新categories的字段，使其+1 
	* @param @param name
	* @param @return  
	* @return Integer
	* @throws
	 */
	Integer addToCategoriesCount(String name);
	/**
	 * 
	* @Title: cutDownCategoriesCount  
	* @Description:  根据name更新categories的字段，使其-1
	* @param @param name
	* @param @return  
	* @return Integer
	* @throws
	 */
	Integer cutDownCategoriesCount(String name);
	
	void removeCategory(Integer id);
	
	Categories saveCategory(Categories categories);
	
	Categories getOneCategory(Integer id);
	
}
