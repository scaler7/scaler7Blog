package com.scaler7.admin.controller;


import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.scaler7.admin.domain.Link;
import com.scaler7.admin.service.ILinkService;
import com.scaler7.common.CodeMsg;
import com.scaler7.common.Constant;
import com.scaler7.common.Result;
import com.scaler7.utils.CommonUtil;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author scaler7
 * @since 2019-12-06
 */
@Controller
@RequestMapping("/admin/links")
public class LinkController {
	
	@Autowired
	ILinkService linkService;
	
	@GetMapping("")
	public ModelAndView loadlAllLinks(ModelAndView modelAndView) {
		List<Link> links = linkService.getLinkList(null);
		modelAndView.addObject("links", links);
		modelAndView.setViewName("admin/links");
		return modelAndView;
	}
	
	@RequiresPermissions("link:insert")
	@RequestMapping("save")
	@ResponseBody
	public Object saveLink(
			@RequestParam(name="name",required = true)
			String name,
			@RequestParam(name="linkUrl",required = true)
			String linkUrl,
			@RequestParam(name="linkDesc",required = false)
			String linkDesc,
			@RequestParam(name="sort",required = true)
			Integer sort
			) {
		Link link = new Link();
		link.setName(name);
		link.setLinkUrl(linkUrl);
		link.setLinkDesc(linkDesc);
		link.setSort(sort);
		link.setCreated(CommonUtil.getTimeFormat(Constant.YYYY_MM_DD_HH_MM_SS));
		Link saveLink = linkService.saveLink(link);
		if(saveLink == null) {
			return new Result(CodeMsg.ADD_ERROR);
		}
		return new Result();
	}
	
	@RequiresPermissions("link:update")
	@RequestMapping("modify")
	@ResponseBody
	public Object modifyLink(
			@RequestParam(name="id",required = true)
			Integer id,
			@RequestParam(name="name",required = true)
			String name,
			@RequestParam(name="linkUrl",required = true)
			String linkUrl,
			@RequestParam(name="linkDesc",required = false)
			String linkDesc,
			@RequestParam(name="sort",required = true)
			Integer sort
			) {
		Link link = new Link();
		link.setId(id);
		link.setName(name);
		link.setLinkUrl(linkUrl);
		link.setLinkDesc(linkDesc);
		link.setSort(sort);
		Link saveLink = linkService.updateLink(link);
		if(saveLink == null) {
			return new Result(CodeMsg.UPDATE_ERROR);
		}
		return new Result();
	}
	
	@RequiresPermissions("link:delete")
	@PostMapping("remove")
	@ResponseBody
	public Object delLink(Integer id) {
		linkService.removeLink(id);
		return new Result();
	}
}
