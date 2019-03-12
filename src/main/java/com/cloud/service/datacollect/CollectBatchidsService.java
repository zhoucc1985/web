package com.cloud.service.datacollect;

public interface CollectBatchidsService {
	/**
	 * 获取导入批次编号
	 * @param name 批次名称
	 * @return
	 */
	String obtainBatchid(String name);
}
