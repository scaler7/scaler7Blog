package com.scaler7.filter;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import com.alibaba.fastjson.JSON;
import com.scaler7.utils.WebUtil;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		
		if(WebUtil.isAjax(request)) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("code", -1);
			resultMap.put("msg", "登陆认证失效，请重新登录");
			PrintWriter out = response.getWriter();
			out.write(JSON.toJSONString(resultMap));
		}else {
			response.reset();
			HttpServletResponse servletResponse = (HttpServletResponse) response;
			servletResponse.sendRedirect("../login.html");
		}
		
		return false;
	}


	
}
