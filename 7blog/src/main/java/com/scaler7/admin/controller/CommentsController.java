package com.scaler7.admin.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.scaler7.admin.domain.Comments;
import com.scaler7.admin.service.ICommentsService;
import com.scaler7.common.Constant;
import com.scaler7.common.Result;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author scaler7
 * @since 2019-12-01
 */
@Controller
@RequestMapping("/admin/comments")
public class CommentsController {
	
	@Autowired
	ICommentsService commentsService;
	
	@GetMapping(value = "")
	public ModelAndView loadAllComments(
			ModelAndView modelAndView,
			@RequestParam(name = "pageNum",required = true,defaultValue = "1")
			Integer pageNum,
			@RequestParam(name = "pageSize",required = true,defaultValue = "10")
			Integer pageSize
			) {
		PageInfo<Comments> comments = commentsService.getCommentsOnePage(pageNum, pageSize);
		modelAndView.addObject("comments", comments);
		modelAndView.setViewName("admin/comment_list");
		return modelAndView;
	}
	
	@RequiresPermissions("comment:delete")
	@PostMapping("delete")
	@ResponseBody
	public Object removeComments(Integer id) {
		commentsService.removeComments(id);
		return new Result();
	}
	
	@RequiresPermissions("comment:pass")
	@PostMapping("pass")
	@ResponseBody
	public Object passComments(Integer id) {
		Comments comments = new Comments();
		comments.setId(id);
		comments.setStatus(Constant.STATUS_PASS);
		commentsService.passComment(comments);
		return new Result();
	}
	
	
	
}
