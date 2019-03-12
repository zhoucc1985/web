package com.cloud.mapper.master.datacollect;

import com.cloud.entity.datacollect.CollectColumns;
import com.cloud.entity.datacollect.CollectRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AsyncExecutorValidMapper {
	List<Map<String, Object>> getColumnFiledsByTableName(@Param(value = "realTableName") List<String> realTableName);
	
	 void insertCollectColumns(CollectColumns collectColumns);
	 
	 void insertCollectRule(CollectRule collectColumns);

	 void truncateCollectColumne();
	 
	 void truncateCollectRule();
}
