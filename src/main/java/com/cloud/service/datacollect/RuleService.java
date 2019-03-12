package com.cloud.service.datacollect;

import java.util.List;

import com.cloud.entity.datacollect.CollectRule;

public interface RuleService {
	public void insertCollectColumns() throws Exception;
	
	List<CollectRule> findCollectRule();
}
