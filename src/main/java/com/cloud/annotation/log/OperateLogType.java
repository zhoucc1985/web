package com.cloud.annotation.log;

/**
 * 操作日志的操作类型
 * @ClassName OperateLogType.java
 * @author shrChang.Liu
 * @date 2017年12月20日下午4:08:46
 */
public enum OperateLogType {

	/**
	 * 枚举定义
	 */
	ADD(1, "新增"), UPDATE(2, "修改"), DEL(3, "删除"), QUERY(4, "查询"),WEBJUMP(5,"网页跳转"),COPY(6,"复制"),DOWN(7,"下载"),IMPORT(8,"导入"),VM_OPERATION(9,"虚拟机操作");

	Integer code;
	String name;

	OperateLogType(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
