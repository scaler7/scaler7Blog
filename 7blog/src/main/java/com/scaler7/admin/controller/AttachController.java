package com.scaler7.admin.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageInfo;
import com.scaler7.admin.domain.Attach;
import com.scaler7.admin.service.IAttachService;
import com.scaler7.common.Result;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author scaler7
 * @since 2019-12-02
 */
@RestController
@RequestMapping("/admin/attach")
public class AttachController {
	
	@Autowired
	IAttachService attachService;
	
	@GetMapping("")
	public ModelAndView loadAllAttach(
			ModelAndView modelAndView,
			@RequestParam(name = "page", required = false, defaultValue = "1")
            int page,
            @RequestParam(name = "limit", required = false, defaultValue = "12")
            int limit) {
		PageInfo<Attach> attachs = attachService.getAttachList(page, limit);
		modelAndView.addObject("attachs", attachs);
		
		modelAndView.setViewName("admin/attach");
		return modelAndView;
	}
	
	@RequiresPermissions("attach:upload")
	@PostMapping("upload")
	@ResponseBody
	public Object upload(
			@RequestParam(name = "file", required = true)
			MultipartFile[] files) {
		Result rs = null;
		for (MultipartFile file : files) {
			rs = attachService.saveFile(file, new Attach());
		}
		return rs;
	}
	
	@RequiresPermissions("attach:delete")
	@PostMapping("delete")
	@ResponseBody
	public Object delete(Integer id) {
		return attachService.removeAttach(id);
	}
	
}
