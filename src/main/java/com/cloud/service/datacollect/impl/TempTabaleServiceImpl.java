package com.cloud.service.datacollect.impl;

import java.util.*;

import com.cloud.mapper.master.datacollect.*;
import com.cloud.mapper.temp.datacollect.TempTableMapper;
import com.cloud.service.datacollect.TempTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.entity.datacollect.CollectTemplate;

import javax.annotation.Resource;



@Service
public class TempTabaleServiceImpl implements TempTableService {
    @Resource
    private UnValidDataDetailMapper unValidDataDetailMapper;
    @Autowired
    private TempTableMapper tempTableMapper;
    @Resource
    private CollectRuleMapper collectRuleMapper;
    @Resource
    private CollectImportDetailMapper collectImportDetailMapper;
    @Resource
    private CollectTemplateMapper collectTemplateMapper;

    @Override
    public Integer deleteTempDateByBatchId(String batchId, String tableName) {
        tempTableMapper.deleteTempDateByBatchId(batchId,tableName);
        return 1;
    }

    @Override
//    public List<Map<String, Object>> findRulesByTableName(String tempTableName){
//        return collectRuleMapper.findRulesByTableName(tempTableName);
    public List<Map<String, Object>> findRulesByRealTableName(String tempTableName){
        String tableName = tempTableName.substring(4);
        return collectRuleMapper.findRulesByRealTableName(tableName);
    }
	@Override
	public  List<CollectTemplate> findAllRealTableName() {
		return collectTemplateMapper.findAllRealTableName();
	}

    @Override
    public List<Map<String, Object>> findTmpTableDataListById(String tmpTableName,String batchId,Long id) {
        List<String> columns = new ArrayList<>();
        List<Map<String,Object>> columnsList = unValidDataDetailMapper.findTmpTableColumnsIncludeId(tmpTableName);
        for(Map<String,Object> map:columnsList){
            columns.add(map.get("en_column").toString());
        }
        //return tempTableMapper.findTmpTableDataList(columns,tmpTableName,id);
        return tempTableMapper.findTmpTableDataList(columns,tmpTableName,id);
    }

    @Override
    public void updateErrorMsg(String tmpTableName,String errorMsgStr,Integer isError,Integer id) {
        //tempTableMapper.updateErrorMsg(tmpTableName, errorMsgStr, isError, id);
        tempTableMapper.updateErrorMsg(tmpTableName, errorMsgStr, isError, id);
    }

    @Override
    public void updateBatchValidResult(String batchId) {
        collectImportDetailMapper.updateBatchValidResult(batchId);
    }

    @Override
    public List<Map<String, Object>> findTmpTableDataListByBatchId(String tmpTableName,String batchId) {
        List<String> columns = new ArrayList<>();
        List<Map<String,Object>> columnList = unValidDataDetailMapper.findTmpTableColumns(batchId,tmpTableName.substring(4));
        columns.add("_batch_id");
        columns.add("_id");
        for(Map<String,Object> map:columnList){
            columns.add(map.get("en_column").toString());
        }
        //return tempTableMapper.findTmpTableDataByBatchId(columns,tmpTableName,batchId);
        return tempTableMapper.findTmpTableDataAll(columns,tmpTableName,batchId);
    }

    @Override
    public Integer exportDataAllCount(Integer templateId,String batchId) {
        String tableName = unValidDataDetailMapper.findTmpTableNameByTempId(templateId);
        //return tempTableMapper.exportDataAllCount(tableName,batchId);
        return tempTableMapper.exportDataAllCount(tableName,batchId);
    }

    @Override
    public Integer exportDataEffectiveCount(Integer templateId,String batchId) {
        String tableName = unValidDataDetailMapper.findTmpTableNameByTempId(templateId);
        //return tempTableMapper.exportDataEffectiveCount(tableName,batchId);
        return tempTableMapper.exportDataEffectiveCount(tableName,batchId);
    }

    @Override
    public Integer exportDataInvalidCount(Integer templateId,String batchId) {
        String tableName = unValidDataDetailMapper.findTmpTableNameByTempId(templateId);
//        return tempTableMapper.exportDataInvalidCount(tableName,batchId);
        return tempTableMapper.exportDataInvalidCount(tableName,batchId);
    }

}
