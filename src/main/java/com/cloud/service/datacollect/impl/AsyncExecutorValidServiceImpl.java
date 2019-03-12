package com.cloud.service.datacollect.impl;

import com.cloud.collectRule.DateValid;
import com.cloud.collectRule.NumberValid;
import com.cloud.collectRule.RegularValid;
import com.cloud.collectRule.StringValid;
import com.cloud.common.utils.CommonUtils;
import com.cloud.common.utils.WebUtils;
import com.cloud.mapper.master.datacollect.AsyncExecutorValidMapper;
import com.cloud.service.datacollect.AsyncExecutorValidService;
import com.cloud.service.datacollect.ExcelService;
import com.cloud.service.datacollect.TempTableService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class AsyncExecutorValidServiceImpl implements AsyncExecutorValidService {
    @Resource
    private AsyncExecutorValidMapper asyncExecutorValidMapper;
    @Resource
    private TempTableService tempTableService;
    @Autowired @Lazy
    private ExcelService excelService;

    @Override
    public boolean validOne(String tmpTableName, Long id, String batchId) {
        try {
            // 查询到当前的数据
            List<Map<String, Object>> dataList = tempTableService.findTmpTableDataListById(tmpTableName, batchId, id);
            // 查询到当前表的规则()
            List<Map<String, Object>> tableRuleList = tempTableService.findRulesByRealTableName(tmpTableName);
            // 规则List转map
            Map<String, Map<String, Object>> tableRules = RuleListToMap(tableRuleList);
            // 当前数据记录
            Map<String, Object> record = dataList.get(0);
            // 一条记录所有列的错误信息
            List<Map<String, Object>> oneRecordErrorMsg = validOneRecord(record, tableRules);
            // 修改当前记录值的错误信息
            updateErrorMsg(oneRecordErrorMsg, tmpTableName);
//            // 修改批次验证结果
//            updateBatchErrorCount(batchId);
            List<Map<String, Object>> msgList=(List<Map<String, Object>>)oneRecordErrorMsg.get(0).get("errorMsgMap");
            if(msgList.size()>0){
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 验证一条记录的多个列值
     *
     * @param record     一条记录
     * @param tableRules 表的所有规则
     * @return Map [id ：id值  "errorMsgMap":map错误信息]
     */
    public List<Map<String, Object>> validOneRecord(Map<String, Object> record, Map<String, Map<String, Object>> tableRules) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> resultMap = new LinkedHashMap<>();
        List<Map<String, Object>> errorMsgMapList = new ArrayList<>();
        for (String columnName : record.keySet()) {                                 //columnName= enColumn,data_type、max_length、is_null
            if (!columnName.equals("_id")) {
                String columnValue = WebUtils.getString(record.get(columnName));//得到每个规则表字段下的数据，columnValue=   code,   varchar,     255,        1
                Map<String, Object> rules = tableRules.get(columnName);//rules= {enColumn:code,data_type:varchar,max_length:255,is_null:1}
                // 单个值验证
                if (rules != null && rules.size() > 0) {
                    String oneColumnErrorMsg = validOneValue(columnValue, columnName, rules);
                    if (StringUtils.isNotEmpty(oneColumnErrorMsg) || oneColumnErrorMsg != null) {
                        try {
                            Map<String, Object> errorMsgMap = new LinkedHashMap<>();
                            errorMsgMap.put("name", columnName);
                            errorMsgMap.put("msg", oneColumnErrorMsg);
                            errorMsgMap.put("value", columnValue);
                            errorMsgMap.put("error", true);
                            errorMsgMapList.add(errorMsgMap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
        resultMap.put("id", record.get("_id"));
        resultMap.put("errorMsgMap", errorMsgMapList);
        resultList.add(resultMap);
        return resultList;
    }

    /**
     * 规则 list转换成 map
     *
     * @param ruleList 规则列表
     * @return
     */
    public Map<String, Map<String, Object>> RuleListToMap(List<Map<String, Object>> ruleList) {
        Map<String, Map<String, Object>> resultMap = new LinkedHashMap<>();
        for (Map<String, Object> map : ruleList) {
            String key = WebUtils.getString(map.get("enColumn"));
            resultMap.put(key, map);
        }
        return resultMap;
    }

    /**
     * 批量验证(验证多条数据)
     *
     * @return
     */
    @Override
    @Async("asyncExecutor")
    public void ValidDatas(String tableName, Long templateId, String batchId) {
        //获取临时表待验证数据
        List<Map<String, Object>> validDatas = getValidDatas(tableName, batchId);
        //System.out.println(validDatas.size());
        for (Map<String, Object> map : validDatas) {
            Long columnId = WebUtils.getLong(map.get("_id"));
            //验证单条数据
            validOne(tableName, columnId, batchId);
        }
        //导入Excel并且校验数据过后更新批次信息（有效、无效、进度）
        excelService.updateCollectImport(WebUtils.getInt(templateId),batchId);

    }

    @Override
    public List<Map<String, Object>> getValidDatas(String tempTableName, String batchId) {

        return tempTableService.findTmpTableDataListByBatchId(tempTableName, batchId);
    }

    @Override
    public List<String> getCloumns(String tableName) {
        return null;
    }

    /**
     * 验证单个值数据
     *
     * @param columnValue 列值 待验证的数据
     * @param columnName  列名
     * @param rules       集合
     * @return
     */
    public String validOneValue(String columnValue, String columnName, Map<String, Object> rules) {
    	 boolean passValid = false;//验证是否通过 true 通过  false 不通过
         // 非空验证
         String resultErrorMsg = validColumnValueEmpty(columnValue, columnName, rules);
         if (StringUtils.isEmpty(resultErrorMsg)) {//非空，通过进入下一个验证
             passValid = true;
         }
         if (passValid) {
             // 关键词验证
           String  msg = validKeyWord(columnValue, columnName, rules);
           if(StringUtils.isNotEmpty(msg)){
         	  resultErrorMsg = msg;
         	  changePassValid(resultErrorMsg);
           }
         }
         if (passValid) {
             // 正则验证
         	 String  msg = validRegular(columnValue, columnName, rules);
             if(StringUtils.isNotEmpty(msg)){
           	  resultErrorMsg = msg;
           	  changePassValid(resultErrorMsg);
             }
         }
         if (passValid) {
             // 具体类型验证
         	String  msg = validColumnType(columnValue, columnName, rules);
             if(StringUtils.isNotEmpty(msg)){
             	  resultErrorMsg = msg;
             	  changePassValid(resultErrorMsg);
             }
         }

         return resultErrorMsg;
    }


    private String validRegular(String columnValue, String columnName, Map<String, Object> rules) {
        String regularStr = WebUtils.getString(rules.get("regex_text"));
        if (StringUtils.isEmpty(regularStr)) {//用StringUtil
            return null;
        }
        return RegularValid.valid(columnValue, regularStr);
    }

    /**
     * 验证错误信息返回
     *
     * @param errorMsg
     * @return
     */
    private boolean changePassValid(String errorMsg) {
        return StringUtils.isEmpty(errorMsg);
    }

    /**
     * 按数据类型验证
     *
     * @param columnValue 数据值
     * @param columnName  数据值对应的字段名列名
     * @param rules       数据库中规则
     * @return
     */
    private String validColumnType(String columnValue, String columnName, Map<String, Object> rules) {
        String ruleType = WebUtils.getString(rules.get("rule_type")).toLowerCase();
        String resultMsg = null;
        //TODO chengt
        //日期
        // 支持修改的类型
        if (ruleType.contains("date") || ruleType.contains("datetime") || ruleType.contains("timestamp")) {
            String formatStr = WebUtils.getString(rules.get("format_Str"));
            resultMsg = DateValid.valid(columnValue, ruleType, formatStr);
        }

        // 数字
        if (ruleType.contains("int") || ruleType.contains("bigint") || ruleType.contains("smallint") || ruleType.contains("float")
                || ruleType.contains("double") || ruleType.contains("decimal") ) {
            //如果数据类型是数字类型int bigint float double decimal
            Long maxValue = WebUtils.getLong(rules.get("max_value"));
            Long minValue = WebUtils.getLong(rules.get("min_value"));
            Integer decimalDigits = WebUtils.getInt(rules.get("decimal_digits"));//小数位数
            resultMsg = NumberValid.valid(columnValue, ruleType, maxValue, minValue, decimalDigits, columnName);
        }

        // 字符串
        if (ruleType.contains("varchar") || ruleType.contains("char")) {
            int length = WebUtils.getInt(rules.get("max_length"));
            resultMsg = StringValid.valid(columnValue, length);
        }

        return resultMsg;
    }

    /**
     * 关键词方式验证
     *
     * @param columnValue
     * @param columnName
     * @param rules
     * @return
     */
    private String validKeyWord(String columnValue, String columnName, Map<String, Object> rules) {
        //TODO chengt
//        if(columnValue.toLowerCase().equals("idCard") || columnValue.toLowerCase().equals("birthDay")){
//
//        }

        return null;
    }

    /**
     * 验证是否非空
     *
     * @param columnValue 字段值
     * @param columnName  字段名称
     * @param rules       验证规则
     * @return String
     */
    private String validColumnValueEmpty(String columnValue, String columnName, Map<String, Object> rules) {
        int isNull = WebUtils.getInt(rules.get("is_null"));
        if (isNull == 1 && columnValue == null) {//1 不允许字段值为空 默认0为空
            return columnName + "的值不能为空！";
        } else {
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getColumnFiledsByTableName(List<String> realTableName) {
        return asyncExecutorValidMapper.getColumnFiledsByTableName(realTableName);
    }

    /**
     * 修改错误信息
     *
     * @param oneRecordErrorMsg 记录错误信息
     * @return
     */
    private void updateErrorMsg(List<Map<String, Object>> oneRecordErrorMsg, String tmpTableName) {
        Integer id = null;
        List<Map<String, Object>> errorMsgMap = new ArrayList<>();
        for (Map<String, Object> map : oneRecordErrorMsg) {
            id = WebUtils.getInt(map.get("id")); // 记录的主键
            errorMsgMap = (List<Map<String, Object>>) map.get("errorMsgMap");
        }
        String errorMsgStr = "";
        Integer isError=1;
        try {
            if (errorMsgMap==null||errorMsgMap.size()<=0){
                isError=0;
            }else{
                errorMsgStr = CommonUtils.getJsonStrByObject(errorMsgMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tempTableService.updateErrorMsg(tmpTableName, errorMsgStr, isError, id);
        return;
    }

    /**
     * 修改批次验证结果
     *
     * @param batchId 批次编号
     * @return
     */
    @Override
    public void updateBatchErrorCount(String batchId) {
        // TODO chengt
    }

    /**
     * 修改为验证完成
     * @param batchId
     */
    @Override
    public void updateBatchValidResult(String batchId) {
        tempTableService.updateBatchValidResult(batchId);
    }


}
