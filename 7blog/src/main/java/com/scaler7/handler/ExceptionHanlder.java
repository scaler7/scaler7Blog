package com.scaler7.handler;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.scaler7.common.CodeMsg;
import com.scaler7.common.Result;
import com.scaler7.utils.WebUtil;

@ControllerAdvice
public class ExceptionHanlder {

	@ExceptionHandler(value={UnauthorizedException.class})
	@ResponseBody
	public Object unAuthorized(ServletRequest request,ServletResponse response) {
		if(WebUtil.isAjax(request)) {
			return new Result(CodeMsg.UNAUTHORIZED_ERROR);
		}else {
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			try {
				httpServletResponse.sendRedirect("/error/403.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}
}
