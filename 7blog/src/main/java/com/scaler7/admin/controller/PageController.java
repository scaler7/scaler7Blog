package com.scaler7.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class PageController {
	@RequestMapping("admin/index")
	public String toIndex() {
		return "admin/index";
	}
	
	@RequestMapping("admin/article_list")
	public String toArticleList() {
		return "admin/article_list";
	}
	
	@RequestMapping("admin/article_edit")
	public String toArticleEdit() {
		return "admin/article_edit";
	}
	
	@RequestMapping("error/403")
	public String to403() {
		return "error/403";
	}
	
	

}
