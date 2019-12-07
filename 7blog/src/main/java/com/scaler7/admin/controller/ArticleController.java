package com.scaler7.admin.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageInfo;
import com.scaler7.admin.domain.Article;
import com.scaler7.admin.domain.Categories;
import com.scaler7.admin.domain.User;
import com.scaler7.admin.service.IArticleService;
import com.scaler7.admin.service.ICategoriesService;
import com.scaler7.admin.vo.CategoriesVO;
import com.scaler7.common.CodeMsg;
import com.scaler7.common.Constant;
import com.scaler7.common.Result;
import com.scaler7.utils.CommonUtil;
import com.scaler7.utils.WebUtil;

@RequestMapping("admin/article")
@Controller
public class ArticleController {
	
	@Autowired
	IArticleService articleService;
	@Autowired
	ICategoriesService categoriesService;
	
	@GetMapping(value = "")
	public ModelAndView loadAllArticle(
			ModelAndView modelAndView,
			@RequestParam(name = "pageNum",required = false,defaultValue = "1")
			int pageNum,
			@RequestParam(name = "limit",required = false,defaultValue = "10")
			int pageSize) {
		
		PageInfo<Article> pageInfo = articleService.getArticleOnePage(pageNum,pageSize);
		modelAndView.addObject("pageInfo", pageInfo);
		modelAndView.setViewName("admin/article_list");
		return modelAndView;
	}
	
	@RequiresPermissions("article:update")
	@GetMapping(value = "{id}")
	public ModelAndView editArticle(
			ModelAndView modelAndView,
			@PathVariable
			Integer id) {
		
		Article article = articleService.getOneArticle(id);
		CategoriesVO categoriesVO = new CategoriesVO();
		List<Categories> categories = categoriesService.getCategoriesList(categoriesVO);
		
		modelAndView.addObject("article", article);
		modelAndView.addObject("categories", categories);
		modelAndView.setViewName("admin/article_edit");
		return modelAndView;
	}
	
	@GetMapping("publish")
	public ModelAndView publish(ModelAndView modelAndView) {
		List<Categories> categories = categoriesService.getCategoriesList(null);
		modelAndView.addObject("categories", categories);
		modelAndView.setViewName("admin/article_edit");
		return modelAndView;
	}
	
	@RequiresPermissions("article:insert")
	@PostMapping("save")
	@ResponseBody
	public Object saveArticle(
            @RequestParam(name = "id", required = true)
            Integer id,
            @RequestParam(name = "title", required = true)
            String title,
            @RequestParam(name = "titlePic", required = false)
            String titlePic,
            @RequestParam(name = "slug", required = false)
            String slug,
            @RequestParam(name = "content", required = true)
            String content,
            @RequestParam(name = "type", required = true)
            String type,
            @RequestParam(name = "status", required = true)
            String status,
            @RequestParam(name = "tags", required = false)
            String tags,
            @RequestParam(name = "categories", required = false, defaultValue = "默认分类")
            String categories,
            @RequestParam(name = "allowComment", required = true)
            Boolean allowComment) {
		Article article = new Article();
		article.setTitle(title);
		article.setId(id);
		article.setTitlePic(titlePic);
		article.setSlug(slug);
		article.setContent(content);
		article.setType(type);
		article.setStatus(status);
		article.setTags(tags);
		article.setCategories(categories);
		article.setAllowComment(allowComment ? 1 : 0);
		article.setHits(0);
		article.setCommentsNum(0);
		article.setCreated(CommonUtil.getTimeFormat(Constant.YYYY_MM_DD_HH_MM_SS));
		article.setModified(CommonUtil.getTimeFormat(Constant.YYYY_MM_DD_HH_MM_SS));
		User user = (User) WebUtil.getSession().getAttribute(Constant.USER);
		article.setAuthorId(user.getId());

		Article resultArticle = articleService.saveArticle(article);
		Integer updateRow = categoriesService.addToCategoriesCount(categories); // 更新categories表count字段值
		if(resultArticle != null && updateRow > 0) {
			return new Result();
		}else {
			return new Result(CodeMsg.ADD_ERROR);
		}
	}
	
	@RequiresPermissions("article:update")
	@PostMapping("modify")
	@ResponseBody
	public Object modifyArticle(
            @RequestParam(name = "id", required = true)
            Integer id,
            @RequestParam(name = "title", required = true)
            String title,
            @RequestParam(name = "titlePic", required = false)
            String titlePic,
            @RequestParam(name = "slug", required = false)
            String slug,
            @RequestParam(name = "content", required = true)
            String content,
            @RequestParam(name = "type", required = true)
            String type,
            @RequestParam(name = "status", required = true)
            String status,
            @RequestParam(name = "tags", required = false)
            String tags,
            @RequestParam(name = "categories", required = false, defaultValue = "默认分类")
            String categories,
            @RequestParam(name = "allowComment", required = true)
            Boolean allowComment) {
		Article article = new Article();
		article.setTitle(title);
		article.setId(id);
		article.setTitlePic(titlePic);
		article.setSlug(slug);
		article.setContent(content);
		article.setType(type);
		article.setStatus(status);
		article.setTags(tags);
		article.setCategories(categories);
		article.setAllowComment(allowComment ? 1 : 0);
		article.setModified(CommonUtil.getTimeFormat(Constant.YYYY_MM_DD_HH_MM_SS));

		Article resultArticle = articleService.updateArticle(article);
		if(resultArticle != null) {
			return new Result();
		}else {
			return new Result(CodeMsg.UPDATE_ERROR);
		}
	}
	
	@RequiresPermissions("article:delete")
	@PostMapping("remove")
	@ResponseBody
	public Object removeArticle(Integer id) {
		Article article = articleService.getOneArticle(id);
		Integer removeRow = articleService.delArticle(id);
		Integer updateRow = categoriesService.cutDownCategoriesCount(article.getCategories()); // 更新categories表count字段值
		if(removeRow > 0 && updateRow > 0) {
			return new Result();
		}
		return new Result(CodeMsg.DELETE_ERROR);
	}
	
}
