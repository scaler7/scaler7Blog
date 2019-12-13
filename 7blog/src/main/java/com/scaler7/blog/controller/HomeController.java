package com.scaler7.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageInfo;
import com.scaler7.admin.domain.Article;
import com.scaler7.admin.domain.Categories;
import com.scaler7.admin.domain.Comments;
import com.scaler7.admin.domain.Link;
import com.scaler7.admin.service.IArticleService;
import com.scaler7.admin.service.ICategoriesService;
import com.scaler7.admin.service.ICommentsService;
import com.scaler7.admin.service.ILinkService;
import com.scaler7.admin.vo.TagsVO;
import com.scaler7.utils.CommonUtil;
import com.scaler7.utils.WebUtil;

@RequestMapping("")
@Controller
public class HomeController {

	@Autowired
	IArticleService articleService;
	@Autowired
	ICommentsService commentsService;
	@Autowired
	ICategoriesService categoriesService;
	@Autowired
	ILinkService linkService;
	
	@RequestMapping("/")
	public ModelAndView home(
			ModelAndView modelAndView,
			@RequestParam(name = "pageNum",required = false,defaultValue = "1")
			int pageNum,
			@RequestParam(name = "limit",required = false,defaultValue = "10")
			int pageSize) {
		PageInfo<Article> articles = articleService.getArticleOnePage(pageNum, pageSize);
		List<Link> links = linkService.getLinkList(null);
		modelAndView.addObject("articles", articles);
		WebUtil.getSession().setAttribute("links", links);
		modelAndView.setViewName("blog/home");
		return modelAndView;
	}
	
	@RequestMapping("blog/archives")
	public ModelAndView archives(
			ModelAndView modelAndView,
			@RequestParam(name = "pageNum",required = false,defaultValue = "1")
			int pageNum,
			@RequestParam(name = "limit",required = false,defaultValue = "10")
			int pageSize) {
		PageInfo<Article> articles = articleService.getArticleOnePage(pageNum, pageSize);
		modelAndView.addObject("articleCount", articles.getSize());
		modelAndView.addObject("articles", articles);
		modelAndView.setViewName("blog/archives");
		return modelAndView;
	}
	
	@GetMapping("blog/detail/{id}")
	public ModelAndView detail(ModelAndView modelAndView,@PathVariable("id") Integer id) {
		
		Article article = articleService.getOneArticle(id); // 根据id获得article
		System.out.println(article);
		article.setHits(article.getHits()+1); // 更新点击量
		articleService.updateArticle(article); // 更新数据库
		
		Comments comments = new Comments();
		comments.setArticleId(id);
		List<Comments> commentsList = commentsService.getCommentsList(comments);
		
		modelAndView.addObject("article", article);
		modelAndView.addObject("comments", commentsList);
		modelAndView.setViewName("blog/detail");
		
		return modelAndView;
	}
	
	@GetMapping("blog/categories")
	public ModelAndView categories(ModelAndView modelAndView) {
		List<Categories> categories = categoriesService.getCategoriesList(null);
		modelAndView.addObject("categories", categories);
		modelAndView.addObject("categoryCount", categories.size());
		modelAndView.setViewName("blog/category");
		return modelAndView;
	}
	
	@GetMapping("blog/categories/{name}")
	public ModelAndView categoriesDetail(
			ModelAndView modelAndView,
			@PathVariable("name")
			String name) {
		Article article = new Article();
		article.setCategories(name);
		List<Article> articles = articleService.getArticleList(article);
		
		modelAndView.addObject("category", name);
		modelAndView.addObject("articles", articles);
		modelAndView.setViewName("blog/category_detail");
		return modelAndView;
	}
	
	@GetMapping("blog/tags")
	public ModelAndView tags(ModelAndView modelAndView) {
		List<Article> articles = articleService.getArticleList(null);
		List<TagsVO> tags = CommonUtil.calcTagsCount(articles);
		
		modelAndView.addObject("tags", tags);
		modelAndView.addObject("tagCount", tags.size());
		modelAndView.setViewName("blog/tags");
		return modelAndView;
	}
	
	@GetMapping("blog/tags/{name}")
	public ModelAndView tagsDetail(
			ModelAndView modelAndView,
			@PathVariable("name")
			String name) {
		Article article = new Article();
		article.setTags(name);
		List<Article> articles = articleService.getArticleList(article);
		modelAndView.addObject("tag", name);
		modelAndView.addObject("articles", articles);
		modelAndView.setViewName("blog/tag_detail");
		return modelAndView;
	}
	
	@GetMapping("blog/about")
	public ModelAndView about(ModelAndView modelAndView) {
		modelAndView.setViewName("blog/about");
		return modelAndView;
	}
	
}
