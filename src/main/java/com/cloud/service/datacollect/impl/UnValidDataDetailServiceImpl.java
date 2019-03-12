package com.cloud.service.datacollect.impl;

import com.alibaba.fastjson.JSONObject;
import com.cloud.common.excption.BusinessException;
import com.cloud.common.utils.*;
import com.cloud.common.vo.datacollection.CollectionDataErrorObjVO;
import com.cloud.entity.datacollection.CollectionBatch;
import com.cloud.entity.datacollection.CollectionDataSource;
import com.cloud.entity.datacollection.ReportTemplatePageField;
import com.cloud.mapper.master.datacollect.TemplateMapper;
import com.cloud.mapper.master.datacollect.UnValidDataDetailMapper;
import com.cloud.mapper.master.datacollection.CollectionBatchMapper;
import com.cloud.mapper.master.datacollection.CollectionDataSourceMapper;
import com.cloud.mapper.master.datacollection.ReportTemplatePageFieldMapper;
import com.cloud.mapper.temp.datacollect.TempTableMapper;
import com.cloud.service.datacollect.AsyncExecutorValidService;
import com.cloud.service.datacollect.ExcelService;
import com.cloud.service.datacollect.UnValidDataDetailService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Author: chengt@sunmnet.com
 * @Date: created on 11:20 2019/1/16
 * @Description:
 */

@Service
public class UnValidDataDetailServiceImpl implements UnValidDataDetailService {

    private static final Logger logger = LoggerFactory.getLogger(UnValidDataDetailServiceImpl.class);
    @Resource
    private UnValidDataDetailMapper unValidDataDetailMapper;
    @Resource
    private CollectionDataSourceMapper sourceMapper;
    @Resource
    private CollectionBatchMapper collectionBatchMapper;
    @Resource
    private AsyncExecutorValidService asyncExecutorValidService;
    @Resource
    private ReportTemplatePageFieldMapper fieldMapper;
    @Autowired
    private ExcelService excelService;
    @Resource
    private TemplateMapper templateMapper;
    @Resource
    private TempTableMapper tempTableMapper;

    /**
     * 无效数据的统计头
     *
     * @param batchId
     * @return
     */
    public Map<String, Object> invalidDataStat(String batchId) {
        Map<String, Object> resultMap = new HashMap<>();
        String tmpTableName = unValidDataDetailMapper.findTmpTableNameByBatchId(batchId); //得到临时表名
        List<Map<String, Object>> columnList = unValidDataDetailMapper.findTmpTableColumns(batchId, tmpTableName.substring(4)); //得到临时表的所有字段名
//        List<Map<String,Object>> columnList = unValidDataDetailMapper.findTmpTableColumnsIncludeId(tmpTableName);
        Map<String, String> columnsMap = new HashMap<>();
        for (Map<String, Object> map : columnList) {
            String column_ch_name = map.get("ch_column").toString();//字段中文名
            String column_en_name = map.get("en_column").toString();//字段英文名
            columnsMap.put(column_en_name, column_ch_name);
        }
        Map<String,Object> dataMap = unValidDataDetailMapper.findInvalidDataStat(batchId);
        String batchid = WebUtils.getString(dataMap.get("batch_id"));
        String loadTime = WebUtils.getString(dataMap.get("load_time"));
        String validNum = WebUtils.getString(dataMap.get("Invalid_num"));
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("batch_id",batchid);
        map.put("load_time",loadTime.substring(0,19));
        map.put("Invalid_num",validNum);
        resultMap.put("statistics", map);//批次、时间、无效数据总数
        resultMap.put("tableColumns", columnList);//表头
        return resultMap;
    }

    /**
     * 查询无效数据列表（新）
     */
    public PageInfo<List<Map<String, Object>>> showInvalidDataList(Integer page, Integer pageSize, String batchId) throws Exception {
        String tableName = unValidDataDetailMapper.findTmpTableNameByBatchId(batchId);//查找表名
        List<Map<String, Object>> invalidDataList = tempTableMapper.findInvalidData(page, pageSize, batchId, tableName);
        List<Map<String,Object>> list = new ArrayList<>();
        //数据库中查出真实表和临时表字段大小写不一致的情况
        if (invalidDataList != null && invalidDataList.size() > 0) {
            for (Map<String, Object> map : invalidDataList) {
                Map<String,Object> map1 = new HashMap<>();
                list.add(map1);
                for(Map.Entry<String, Object> entry : map.entrySet()){
                    map1.put(entry.getKey().toLowerCase(),map.get(entry.getKey()));
                }

            }

            for(Map<String,Object> map:list){
                String _error_msg = WebUtils.getString(map.get("_error_msg"));
                Integer isError = WebUtils.getInt(map.get("_is_error"));
                Boolean error;
                if(isError == 1){
                    error = true;
                }else{
                    error = false;
                }
                map.put("error",error);
                if (StringUtils.isNotEmpty(_error_msg)) {
                    List<Object> errormsgList = JSONObject.parseArray(_error_msg);
                    Map<String, Object> allErrorMap = new HashMap<>();
                    for (Object obj : errormsgList) {
                        Map<String, Object> errormap = (Map<String, Object>) obj;
                        String key = WebUtils.getString(errormap.get("name"));
                        allErrorMap.put(key, errormap);
                    }
                    for (String key : allErrorMap.keySet()) {
                        // allErrorMap.put("value", map.get(key));
                        map.put(key.toLowerCase(), allErrorMap.get(key));
                    }
                }
            }
        }
        PageInfo<List<Map<String, Object>>> pageInfo = new PageInfo(list);
        pageInfo.setPageNum(page);
        pageInfo.setPageSize(pageInfo.getPageSize());
        pageInfo.setTotal(tempTableMapper.findInvalidDataTotalCount(batchId, tableName));//总条数
        return pageInfo;
    }

    /**
     * 修改后保存数据（新的 传入对象）
     *
     * @param batchId 批次编号
     * @param id      每条无效数据的唯一ID
     */
    @Override
    public Map<String, Object> updateByEntity(String batchId, Long id, Map<String, Object> columnDataMap) {
        String tableName = unValidDataDetailMapper.findTmpTableNameByBatchId(batchId);//表名
        List<String> columns = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        for (Map.Entry<String, Object> vo : columnDataMap.entrySet()) {
            columns.add(vo.getKey());
            values.add(vo.getValue());
        }
        String SQL = "";
        for (int i = 0; i < columns.size(); i++) {
            if (i == (columns.size() - 1)) {
                SQL += "`" + columns.get(i) + "` = '" + values.get(i) + "'";

            } else {
                SQL += "`" + columns.get(i) + "` = '" + values.get(i) + "',";
            }

        }
        // 修改数据后保存
        //unValidDataDetailMapper.updateByEntity(SQL, id, tableName);
        tempTableMapper.updateByEntity(SQL, id, tableName);

        //进行数据校验
        boolean vallid= asyncExecutorValidService.validOne(tableName, id, batchId);

        //得到批次Id
        Integer templateId = templateMapper.findTemplateIdByBatchId(batchId);
        //导入Excel并且校验数据过后 更新批次信息（有效、无效、进度）
        excelService.updateCollectImport(templateId,batchId);

        if(!vallid){
            return CommonUtils.getResultMap("验证不通过", "fail");
        }
        return CommonUtils.getSucResultMap();
    }

    /**
     * 删除数据
     *
     * @param ids     id(每个临时表的每条数据都有一个唯一的Id)
     * @param batchId 批次Id
     */
    public void deleteInvalidData(List<String> ids, String batchId) {

        String tmpTableName = unValidDataDetailMapper.findTmpTableNameByBatchId(batchId);//得到表名
        //this.unValidDataDetailMapper.deleteInvalidData(ids, tmpTableName);
        this.tempTableMapper.deleteInvalidData(ids, tmpTableName);
        //删除无效数据过后  更新导入详情表
        Integer templateId = templateMapper.findTemplateIdByBatchId(batchId);
        excelService.updateCollectImport(templateId,batchId);
    }

    /**
     * 导出查询校验错误列表
     *
     * @param batchId
     * @return java.util.List<java.util.Map   <   java.lang.String   ,   com.cloud.common.vo.datacollection.CollectionDataErrorObjVO>>
     * @author Pan Jianneng
     * @date 2018/11/23 1:57 PM
     */
    public List<Map<String, CollectionDataErrorObjVO>> getCollectionDataSourceErrorsByBatchId(String batchId) {
        List<Map<String, CollectionDataErrorObjVO>> result = new ArrayList<Map<String, CollectionDataErrorObjVO>>();
        List<CollectionDataSource> errorSources = null;//sourceMapper.findErrorsByBatchId(batchId);
        if (ObjectUtils.isNotEmptyList(errorSources)) {
            result = CollectionDataVoConvertUtils.convertToErrorListByList(
                    errorSources);
        }
        return result;
    }

    /**
     * 无效数据列表导成Excel -- 旧代码 无用！
     *
     * @param batchId
     * @param response
     */
    public void exportInvalidData(Long batchId, HttpServletResponse response) {
        CollectionBatch batch = collectionBatchMapper.selectByPrimaryKey(batchId);
        if (ObjectUtils.isNullObject(batch)) {
            throw new BusinessException("没有找到错误数据所属批次");
        }
        List<Map<String, CollectionDataErrorObjVO>> errorList = null;//getCollectionDataSourceErrorsByBatchId(batch.getId());
        List<ReportTemplatePageField> rtFields = fieldMapper.findByReportTemplateId(batch.getRtId());
        if (ObjectUtils.isEmptyList(rtFields)) {
            throw new BusinessException("获取表头失败");
        }
        LinkedHashMap<String, String> header = new LinkedHashMap<String, String>();
        rtFields.forEach(rtField -> {
            header.put(rtField.getCode(), rtField.getName());
        });
        try {
            // 声明一个工作薄
            XSSFWorkbook workbook = new XSSFWorkbook();
            //所有单元格上锁
            ExcelUtil.lockStyle(workbook);
            // 生成一个表格
            XSSFSheet sheet = workbook.createSheet("迎新数据");
            //设置要导出的文件的名字
            String fileName = "迎新数据new-" + batch.getBatchNo() + "-错误数据" + ".xlsx";
            // 设置表格默认列宽度为15个字节
            sheet.setDefaultColumnWidth(15);
            // 声明一个画图的顶级管理器
            XSSFDrawing patriarch = sheet.createDrawingPatriarch();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            response.flushBuffer();
            //设置隐藏列，保存批次ID
            /// TODO ExcelUtil.setNoneCell(workbook,sheet,String.valueOf(batch.getId()));
            ExcelUtil.transform(workbook, sheet, patriarch, header, errorList, response.getOutputStream());
        } catch (Exception e) {
            logger.error("导出错误列表失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Map<String, Object>> findTableErrorList(String batchId, String tableName) {
//        return unValidDataDetailMapper.findTableErrorList(batchId, tableName);
        List<Map<String,Object>> columns = unValidDataDetailMapper.findTmpTableColumns(batchId,tableName);
        List<String> columnsList = new ArrayList<>();
        columns.forEach(map -> {
            columnsList.add(map.get("en_column").toString());
        });
        columnsList.add("_id");
        return tempTableMapper.findTableErrorList(columnsList,batchId, tableName);
    }

}
