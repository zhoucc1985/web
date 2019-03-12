package com.cloud.service.datacollect.impl;

import com.cloud.entity.datacollect.CollectColumns;
import com.cloud.entity.datacollect.CollectTemplate;
import com.cloud.mapper.master.datacollect.CollectImportDetailMapper;
import com.cloud.mapper.master.datacollect.CollectRuleMapper;
import com.cloud.mapper.master.datacollect.CollectTemplateMapper;
import com.cloud.mapper.real.datacollect.RealTableMapper;
import com.cloud.mapper.temp.datacollect.TempTableMapper;
import com.cloud.service.datacollect.RealTableService;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service
public class RealTableServiceImpl implements RealTableService {
    @Resource
    private TempTableMapper tempTableMapper;
    @Resource
	private RealTableMapper realTableMapper;
    @Resource
	private CollectImportDetailMapper collectImportDetailMapper;
    @Resource
	private CollectTemplateMapper collectTemplateMapper;
    @Resource
	private CollectRuleMapper collectRuleMapper;


    @Override
    public Integer deleteRealTableDataByBatchId(String batchId, String tableName) {
		realTableMapper.deleteRealTableDataByBatchId(batchId,tableName);
        return 1;
    }
	@Override
	public void insertRealTableName(String resultStr, String tempTableName, String realTableName,String batchId) {
		tempTableMapper.insertRealTableName(resultStr, tempTableName, realTableName,batchId);
	}
	@Override
	public void updateImportDetail(String batchId) {
		collectImportDetailMapper.updateImportDetail(batchId);
	}
	@Override
	public List<CollectTemplate> findAllRealTableName() {
		return collectTemplateMapper.findAllRealTableName();
	}
	@Override
	public List<CollectColumns> findCollectColumns() {
		return collectRuleMapper.findCollectColumns();
	}

}
