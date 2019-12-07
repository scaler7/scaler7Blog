package com.scaler7.common;

/**
 * 
* @ClassName: scaler7
* @Description: ajax请求时，返回给前端的响应信息，可以携带数据，也可以不携带 
* @author Zzz  
* @date 2019年12月4日  
*
 */
public class Result {
	
	Integer code;
	String msg;
	Object data;
	
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
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	/**
	 * 	操作成功，没有返回数据
	 */
	public Result() {
		this.code = CodeMsg.SUCCESS.code;
		this.msg = CodeMsg.SUCCESS.msg;
	}
	
	/**
	 *	 操作失败
	 * @param codeMsg
	 */
	public Result(CodeMsg codeMsg) {
		this.msg = codeMsg.getMsg();
		this.code = codeMsg.getCode();
	}
	
	/**
	 *	操作成功，有数据返回
	 * @param data
	 */
	public Result(Object data) {
		this.data = data;
		this.code = CodeMsg.SUCCESS.code;
		this.msg = CodeMsg.SUCCESS.msg;
	}
	
	public Result(String msg,Integer code) {
		this.msg = msg;
		this.code = code;
		
	}
	@Override
	public String toString() {
		return "Result [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
	
	
	
}
