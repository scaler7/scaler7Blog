package com.scaler7.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.scaler7.admin.domain.Article;
import com.scaler7.admin.domain.Comments;
import com.scaler7.admin.domain.Loginlog;
import com.scaler7.admin.service.IArticleService;
import com.scaler7.admin.service.IAttachService;
import com.scaler7.admin.service.ICommentsService;
import com.scaler7.admin.service.ILinkService;
import com.scaler7.admin.service.ILoginlogService;

@Controller
@RequestMapping("admin/index")
public class IndexController {

	@Autowired
	IArticleService articleService;
	@Autowired
	ICommentsService commentsService;
	@Autowired
	ILoginlogService loginlogService;
	@Autowired
	IAttachService attachService;
	@Autowired
	ILinkService linkService;
	
	@GetMapping("")
	public ModelAndView index(ModelAndView modelAndView) {
		PageInfo<Article> articles = articleService.getArticleOnePage(1, 5);
		PageInfo<Comments> comments = commentsService.getCommentsOnePage(1, 5);
		PageInfo<Loginlog> loginlogs = loginlogService.getLoginlogOnePage(1, 5);
		
		modelAndView.addObject("articlesPageInfo", articles);
		modelAndView.addObject("commentsPageInfo", comments);
		modelAndView.addObject("loginlogs", loginlogs);
		
		int articleCount = articleService.count();
		int commentsCount = commentsService.count();
		int attachCount = attachService.count();
		int linkCount = linkService.count();
		
		modelAndView.addObject("articleCount", articleCount);
		modelAndView.addObject("commentsCount", commentsCount);
		modelAndView.addObject("attachCount", attachCount);
		modelAndView.addObject("linkCount", linkCount);
		
		modelAndView.setViewName("admin/index");
		
		return modelAndView;
	}
	
}
