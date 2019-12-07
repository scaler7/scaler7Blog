package com.scaler7.admin.controller;

import javax.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scaler7.admin.domain.Loginlog;
import com.scaler7.admin.domain.User;
import com.scaler7.admin.service.ILoginlogService;
import com.scaler7.common.ActiveUser;
import com.scaler7.common.CodeMsg;
import com.scaler7.common.Constant;
import com.scaler7.common.Result;
import com.scaler7.utils.CommonUtil;
import com.scaler7.utils.WebUtil;

@Controller
@RequestMapping("admin/login")
public class LoginController {
	
	@Autowired
	ILoginlogService loginlogService;

	@PostMapping("")
	@ResponseBody
	public Object login(String username, String password) {
		HttpSession session = WebUtil.getSession();

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
			ActiveUser activeUser = (ActiveUser) subject.getPrincipals().getPrimaryPrincipal();
			User user = activeUser.getUser();
			session.setAttribute("user", user);
			// 写入日志
			loginlogService.save(new Loginlog(
					0, // id 
					user.getName(), // 登陆用户名 
					WebUtil.getRequest().getRemoteAddr(), // 登陆ip
					CommonUtil.getTimeFormat(Constant.YYYY_MM_DD_HH_MM_SS))); // 格式化登陆时间
			return new Result();
		} catch (Exception e) {
			return new Result(CodeMsg.LOGIN_ERROR);
		}
	}

}
