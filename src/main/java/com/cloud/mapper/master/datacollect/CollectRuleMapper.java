package com.cloud.mapper.master.datacollect;

import java.util.List;
import java.util.Map;

import com.cloud.entity.datacollect.CollectColumns;
import org.apache.ibatis.annotations.Param;

import com.cloud.entity.datacollect.CollectRule;

public interface CollectRuleMapper {
	List<CollectRule> findCollectRule();
	List<CollectRule> findColDetail(@Param("page") Integer page,@Param("pageSize") Integer pageSize,
            @Param("template_name") String template_name);
	
	int findColDetailTotal(String template_name);
	
	int updateCollectRule(CollectRule rule);
	
	int deleteCollectRule(String columnId);

	/**
	 * 根据临时表名和字段ID查询对应规则
	 * @param tempTableName
	 * @return
	 */
	List<Map<String,Object>> findRulesByTableName(@Param("tempTableName") String tempTableName);

	/**
	 * 根据真实表名查询表字段规则
	 * @param tableName
	 * @return
	 */
	List<Map<String,Object>> findRulesByRealTableName(@Param("tableName") String tableName);

	List<CollectColumns> findCollectColumns();
}
