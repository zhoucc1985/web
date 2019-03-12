package com.cloud.mapper.master.datacollect;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cloud.entity.datacollect.CollectBatchids;

@Mapper
public interface CollectBatchidsMapper {
	CollectBatchids findCollectBatchids(String date);

	void insertCollectBatchids(CollectBatchids collectBatchids);

	void updateCollectBatchids(@Param("name") String name, @Param("date") String date);
}
