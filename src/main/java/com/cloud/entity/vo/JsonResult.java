package com.cloud.entity.vo;

/**
 * 封装json结果集
 */
public class JsonResult {

	/** 返回是否成功  **/
	private Boolean success = true;

	/** 返回信息 **/
	private String msg = "";

	/** 返回其他对象信息  **/
	private Object obj = null;
	
	/**	异常时的错误编码，用于一些情况下需要客户端根据不同的错误编码进行进一步操作 */
	private Integer errorCode = null;

	public JsonResult() {
		this.success = true;
	}

	public JsonResult(Boolean success, String msg, Object obj, Integer errorCode) {
        super();
        this.success = success;
        this.msg = msg;
        this.obj = obj;
        this.errorCode = errorCode;
    }


    public JsonResult(Boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public JsonResult(Boolean success) {
		this.success = success;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
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

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String toString() {
		return "JsonResult{" +
				"success=" + success +
				", msg='" + msg + '\'' +
				", obj=" + obj +
				", errorCode=" + errorCode +
				'}';
	}
}