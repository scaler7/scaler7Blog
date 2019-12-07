package com.scaler7.common;

/**
 * 
* @ClassName: scaler7
* @Description: 消息类，用于返回给前端指定格式的相应信息 
* @author Zzz  
* @date 2019年12月4日  
*
 */
public enum CodeMsg {
	
	
	SUCCESS(200,"OK"),
	
	LOGIN_ERROR(3001001,"登陆失败！"),
	
	UNAUTHORIZED_ERROR(5001403,"权限不足！"),
	
	QUERY_ERROR(4001001,"查询失败！"),
	ADD_ERROR(4002002,"添加失败！"),
	DELETE_ERROR(4002003,"删除失败！"),
	UPDATE_ERROR(4002004,"修改失败！"),
	
	UPLOAD_ERROR(4002005,"上传失败！"),
	
	//用户登陆消息
	USER_LOGIN_ERROR(2001001,"用户名密码错误！");
	
	public Integer code;
	public String msg;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	private CodeMsg(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	
	
}
