package com.scaler7.admin.controller;

import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.scaler7.admin.domain.Article;
import com.scaler7.admin.domain.Categories;
import com.scaler7.admin.service.IArticleService;
import com.scaler7.admin.service.ICategoriesService;
import com.scaler7.admin.vo.CategoriesVO;
import com.scaler7.admin.vo.TagsVO;
import com.scaler7.common.CodeMsg;
import com.scaler7.common.Result;
import com.scaler7.utils.CommonUtil;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author scaler7
 * @since 2019-11-29
 */
@Controller
@RequestMapping("admin/categories")
public class CategoriesController {
	
	@Autowired
	ICategoriesService categoriesService;
	@Autowired
	IArticleService articleService;
	
	@GetMapping("")
	public ModelAndView loadAllCategories(ModelAndView modelAndView) {
		List<Categories> categories = categoriesService.getCategoriesList(new CategoriesVO());
		
		List<Article> articles = articleService.getArticleList(null);
		
		List<TagsVO> tags = CommonUtil.calcTagsCount(articles);
		
		modelAndView.addObject("categories", categories);
		modelAndView.addObject("tags",tags);
		modelAndView.setViewName("admin/category");
		return modelAndView;
	}
	
	@RequiresPermissions("category:insert")
	@PostMapping("save")
	@ResponseBody
	public Object saveCategory(String name) {
		Categories categories = new Categories();
		categories.setName(name);
		categories.setId(0);
		categoriesService.saveCategory(categories);
		return new Result();
	}
	
	@RequiresPermissions("category:delete")
	@PostMapping("delete")
	@ResponseBody
	public Object removeCategory(Integer id) {
		Categories categories = categoriesService.getOneCategory(id); // 根据id，获取categories数据
		Article article = new Article(); 
		article.setCategories(categories.getName());
		List<Article> articles = articleService.getArticleList(article); // 根据categories的name字段，查询是否有文章属于这个分类
		if(CollectionUtils.isNotEmpty(articles)) { // 如果有，不允许删除分类
			return new Result(CodeMsg.DELETE_ERROR);
		}
		categoriesService.removeCategory(id);
		return new Result();
	}
	
}
