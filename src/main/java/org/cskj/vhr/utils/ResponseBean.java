package org.cskj.vhr.utils;

/*
 *	专门处理响应的数据格式
 */
public class ResponseBean {

	// 状态码
	private Integer status;
	// 消息
	private String msg;
	// 返回的数据对象
	private Object obj;
	
	// 登录成功时要返回的数据格式
	public static ResponseBean ok(String msg,Object obj) {
		return new ResponseBean(200, msg, obj);
	}
	// 登录成功时要返回的数据格式
	public static ResponseBean ok(String msg) {
		return new ResponseBean(200, msg, null);
	}
	// 登录失败时要返回的数据格式
	public static ResponseBean error(String msg) {
		return new ResponseBean(500,msg,null);
	}
	// 登录失败时要返回的数据格式
	public static ResponseBean error(String msg,Object obj) {
		return new ResponseBean(500, msg, obj);
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public ResponseBean(Integer status, String msg, Object obj) {
		super();
		this.status = status;
		this.msg = msg;
		this.obj = obj;
	}

	public ResponseBean() {
		super();
	}

}
