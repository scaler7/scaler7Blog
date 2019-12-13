package com.scaler7.admin.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.scaler7.admin.domain.Setting;
import com.scaler7.admin.service.ISettingService;
import com.scaler7.common.Result;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author scaler7
 * @since 2019-12-12
 */
@RestController
@RequestMapping("admin/setting")
public class SettingController {
	
	@Autowired
	ISettingService settingService;
	
	@GetMapping("")
	public ModelAndView loadAllSetting(ModelAndView modelAndView) {
		List<Setting> settingList = settingService.getSettingList();
		Map<String, String> settings = new HashMap<String, String>();
		for (Setting setting : settingList) {
			settings.put(setting.getSkey(), setting.getSvalue());
		}
		modelAndView.addObject("settings", settings);
		modelAndView.setViewName("admin/setting");
		
		return modelAndView;
	}
	
	@PostMapping
	@ResponseBody
	@RequiresPermissions("setting:update")
	public Object updateSetting(HttpServletRequest request) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map = request.getParameterMap();
		List<Setting> settings = new ArrayList<Setting>();
		Set<Entry<String, String[]>> entrySet = map.entrySet();
		for (Entry<String, String[]> entry : entrySet) {
			Setting setting = new Setting();
			setting.setSkey(entry.getKey());
			setting.setSvalue(entry.getValue()[0]);
			settings.add(setting);
		}
		settingService.batchUpdateSetting(settings);
		return new Result();
	}
	
}
