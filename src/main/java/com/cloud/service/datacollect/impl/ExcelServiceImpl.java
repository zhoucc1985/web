package com.cloud.service.datacollect.impl;

import com.cloud.common.excption.BusinessException;
import com.cloud.common.utils.*;
import com.cloud.common.vo.ShiroUser;
import com.cloud.entity.datacollect.CollectImportDetail;
import com.cloud.mapper.master.datacollect.CollectImportDetailMapper;
import com.cloud.mapper.master.datacollect.ExcelMapper;
import com.cloud.mapper.master.datacollect.UnValidDataDetailMapper;
import com.cloud.mapper.temp.datacollect.TempTableMapper;
import com.cloud.service.datacollect.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Excel导入导出服务类
 */
@Service
public class ExcelServiceImpl implements ExcelService {

  //   private static final Logger logger = LoggerFactory.getLogger(ExcelService.class);
    /**
     * 文件上传临时保存目录地址
     */
    @Value("${user.home}/collectiondata/uploadtempfile")
    private String uploadFileTempPath;

    @Resource
    private ExcelMapper excelMapper;
    @Resource
    private DataCollectService dataCollectService;
    @Resource
    private CollectImportDetailMapper collectImportDetailMapper;
    @Resource
    private UnValidDataDetailMapper unValidDataDetailMapper;
    @Resource @Lazy
    private AsyncExecutorValidService asyncExecutorValidService;
    @Resource
    private TempTableService tempTableService;
    @Autowired
    private TempTableMapper tempTableMapper;
    @Resource
    private CollectBatchidsService collectBatchidsService;

    /**
     * 1、直接导入Excel
     */
    @Override
    public Map<String, Object> importExeclData(MultipartFile file, Integer templateId) throws Exception {
        String batchId = collectBatchidsService.obtainBatchid("collect");
        Map<String, Object> resultmap=importExeclData(file, templateId,batchId);
        String tempFilePath = saveExcelTemp(file, batchId);
        //导入Excel成功 添加一条导入批次记录
        insertCollectImport(templateId, batchId);
        FileUtil.deleteFile(tempFilePath);
        return resultmap;
    }

    /**
     * 导入Excel方法封装
     *
     * @param file       Excel文件
     * @param templateId 模板Id
     * @param batchId    批次Id
     * @throws Exception 异常信息
     */
    private Map<String, Object> importExeclData(MultipartFile file, Integer templateId, String batchId) throws Exception {
        if (ObjectUtils.isEmptyString(file.getOriginalFilename())) {
            throw new BusinessException("请选择上传文件");
        }
        Map<String, Object> resultMap = CommonUtils.getWarnResultMap("正在处理导入的文件，请稍后，过程中您可以做其他操作。");
        //1、上传文件到临时目录
        String tempFilePath = saveExcelTemp(file, String.valueOf(templateId));
        //2、读取excel文件内容
        List<Map<String, Object>> excelDataListMap = MyExcelUtil.getDatesFromFile(tempFilePath);
        //3、Excel数据格式转换
        List<List<String>> excelDataList = excelDataConvert(excelDataListMap);

        // TODO  实现读取Excel全部数据，但是拼接SQL的时候 每次拼接一千条

        String TableNameReal = excelMapper.findTmpTableName(templateId);//表名
        String tmpTableName = "tmp_" + TableNameReal;
        List<String> columnEnNameList = getColumnsEnNames(TableNameReal);//列英文名
        columnEnNameList.add("_batch_id");
        String valuesSQL = joinSQL(excelDataList,batchId);
        String columnSql = StringUtils.join(columnEnNameList.toArray(),",");
        //执行Excel数据导入数据库临时表----
        if (StringUtils.isNotEmpty(tmpTableName) && StringUtils.isNotEmpty(columnSql) && StringUtils.isNotEmpty(valuesSQL)) {
//            excelMapper.insertIntoTmpTable(tmpTableName, columnSql, valuesSQL);
            tempTableMapper.insertIntoTmpTable(tmpTableName, columnSql, valuesSQL);
        }
        //System.out.println("values sql：************\n" + valuesSQL);
        // new一个线程去实现 批量(多条)数据验证
        asyncExecutorValidService.ValidDatas(tmpTableName, Long.valueOf(templateId),batchId);

        return resultMap;
    }

    /**
     * 获取英文列名称
     *
     * @param tmpTableName 临时表名称
     * @return 英文列名称列表
     */
    private List<String> getColumnsEnNames(String tmpTableName) {
        List<Map<String, Object>> columns = excelMapper.findTmpTableColumns(tmpTableName);//所有字段名
        List<String> en_columns_list = new ArrayList<>();//数据库查出的临时表所有字段名
        for (Map<String, Object> map : columns) {
            String column_en_name = map.get("en_column").toString();//字段英文名
            en_columns_list.add(column_en_name);
        }
        return en_columns_list;
    }


    /**
     * 临时保存导入的文档，返回保存目录
     *
     * @param file
     * @param batchId
     * @return
     */
    private String saveExcelTemp(MultipartFile file, String batchId) {
        ShiroUser userInfo = UserUtils.getInstance().getCurrentlyUserInfo();
        //导入数据临时文件保存位置
        String tempSavePath = uploadFileTempPath + "/";
        if (ObjectUtils.isNotNullObject(userInfo)) {
            tempSavePath += userInfo.loginName + "/" + userInfo.getOrgId() + "/" + batchId + "/" + System.currentTimeMillis() + "/";
        } else {
            tempSavePath += System.currentTimeMillis() + "/";
        }


        String fileName = file.getOriginalFilename();
        //路径校验，恶心，直接写是个魔法值
        String index = "\\";
        if (fileName.indexOf(index) != -1) {
            fileName = fileName.substring(fileName.lastIndexOf("\\"));
        }
        File targetFile = new File(tempSavePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(tempSavePath + fileName);
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempSavePath + fileName;
    }
    /**
     * 将Excel解析后的List<Map<String,Object>>数据转换成List<List<String>>
     *
     * @param excelDataMap Excel表格解析后的List<Map<String,Object>>结果集
     * @return
     */
    public List<List<String>> excelDataConvert(List<Map<String, Object>> excelDataMap) {
        List<List<String>> resultList = new ArrayList<>();

        for (Map<String, Object> map : excelDataMap) {
            List<String> excelDataList = new ArrayList<>();
            map.forEach((key, value) -> {
                excelDataList.add((String) value);
            });
            resultList.add(excelDataList);
        }
        return resultList;
    }

    /**
     * 插入数据库SQL values拼接（insert into ${table} values(?,?,?,?,?)的内容）
     * @param excelDataList List<List<String>>-Excel表中的数据
     * @param batchId 批次Id
     * @return
     */
    private static String joinSQL(List<List<String>> excelDataList,String batchId) {
        String resultStr = "";
        for (List<String> list : excelDataList) {
            //resultStr += "('"+StringUtils.join(list.toArray(),"','")+"','"+batchId+"'),";
            resultStr += "('"+StringUtils.join(list.toArray(),"','")+"','"+batchId+"'),";
        }
//        String resultStr = "";
//        String valuesSQL = "";//SQL语句拼接
//        for(List<String> list:excelDataList){
//            String str1="(";
//            for(int i=0;i<list.size();i++){
//                str1+= "'"+list.get(i)+"',";
//            }
//            str1=str1.substring(0,str1.length()-1);
//            str1+="),";
//            resultStr += str1;
//            valuesSQL = resultStr.substring(0,resultStr.length()-1);
//        }
//        System.out.println("Excel表中的所有数据=\n"+excelDataList);
//        System.out.println("Value SQL=\n"+valuesSQL);
        return  resultStr.substring(0, resultStr.length() - 1);
    }

    /**
     * 导入Excel成功 添加一条导入批次记录
     * ** 还未做校验  数据全部有效
     * @param templateId  模板编号
     * @param batchId  批次编号
     */
    private void insertCollectImport(Integer templateId, String batchId) {
        CollectImportDetail collectImportDetail = new CollectImportDetail();
        collectImportDetail.setTemplateId(templateId);
        //导入详情-Excel导入数据总数
        Integer totalCount = tempTableService.exportDataAllCount(templateId,batchId);
        collectImportDetail.setTotalNum(totalCount);
        //有效数据总数（Excel导入后 默认全部有效）
        Integer effectiveCount = tempTableService.exportDataEffectiveCount(templateId,batchId);
        collectImportDetail.setEffectiveNum(effectiveCount);
        //无效数据总数
        Integer InvalidCount = tempTableService.exportDataInvalidCount(templateId,batchId);
        collectImportDetail.setInvalidNum(InvalidCount);
        collectImportDetail.setLoadTime(new Date());
        collectImportDetail.setBatchId(batchId);
        collectImportDetail.setValidType(1);
        //权限控制加入orgid
        String orgId = UserUtils.getInstance().getFindDataOrgCode();
        collectImportDetail.setDeptCode(orgId);
        collectImportDetailMapper.insert(collectImportDetail);
    }

    /**
     * Excel 数据导入 **后台校验完成后**
     * 修改有效、无效数据量，以及验证进度
     */
    @Override
    public void updateCollectImport(Integer templateId, String batchId){
        //统计无效数据和改为校验完成
        //updateBatchErrorCountAndType(batchId);
        CollectImportDetail collectImportDetail = new CollectImportDetail();
        collectImportDetail.setTemplateId(templateId);
        //总数
        Integer totalCount = tempTableService.exportDataAllCount(templateId,batchId);
        collectImportDetail.setTotalNum(totalCount);
        //验证后有效数据总数
        Integer effectiveCount = tempTableService.exportDataEffectiveCount(templateId,batchId);
        collectImportDetail.setEffectiveNum(effectiveCount);
        //验证后无效数据总数
        Integer InvalidCount = tempTableService.exportDataInvalidCount(templateId,batchId);
        collectImportDetail.setInvalidNum(InvalidCount);
        collectImportDetail.setBatchId(batchId);
        //更改验证进度
        collectImportDetail.setValidType(2);
        collectImportDetailMapper.updateCountType(collectImportDetail);
    }

    /**
     * 继续导入 两种情况：1.新增Excel数据（无唯一Id） 2.导入修改后的Excel数据（Excel带入唯一Id）
     * @param file  文件对象
     * @param batchId  批次编号
     */
    @Override
    public Map<String, Object> continueImportData(MultipartFile file, String batchId) throws Exception {

        if (ObjectUtils.isEmptyString(file.getOriginalFilename())) {
            throw new BusinessException("请选择上传文件");
        }
        Map<String, Object> jsonResult = CommonUtils.getWarnResultMap("正在处理导入的文件，请稍后，过程中您可以做其他操作。");
        // ++临时保存数据文档++
        String tempFilePath = saveExcelTemp(file, batchId);
        System.out.println("上传文件 >> " + tempFilePath);

        List<Map<String, Object>> excelDataListMap = MyExcelUtil.getDatesFromFile(tempFilePath);     //Excel数据解析
        String tmpTableName = unValidDataDetailMapper.findTmpTableNameByBatchId(batchId);//表名
        String realTableName = tmpTableName.substring(4);
        //System.out.println("+++++++++++继续导入Excel读取的数据：\n" + excelDataListMap);
//          解析Excel数据，去判断Excel数据的表头是否有“导出编号”，
//          1.如果表头有“导出编号”，则说明是当前批次之前导出修改后的数据（执行删除库中相同唯一Id数据，插入修改后的Excel数据）
//          2.如果没有，则说明是用户新增数据（执行插入数据）

        if (excelDataListMap.get(0).containsKey("导出编号")) {
            //执行导入修改后的Excel数据
            System.out.println("执行【导入修改后的Excel数据】。。。。。。。。。。。。。");
            List<String> uniqueIdList = new ArrayList<>();//导出编号：（Excel导入的每条数据的唯一ID）
            for (Map<String, Object> map : excelDataListMap) {
                uniqueIdList.add(map.get("导出编号").toString());
            }
            //System.out.println("导出编号list：\n" + uniqueIdList);

            //去数据库临时表中删掉编号对应的无效数据
//            excelMapper.delInvalidData(tmpTableName, uniqueIdList);
            tempTableMapper.delInvalidData(tmpTableName, uniqueIdList);

            //解析Excel表格数据为List<List<String>>集合
            List<List<String>> excelDataList = excelDataConvert(excelDataListMap);
            System.out.println("解析Excel表格数据为List<List<String>> :\n" + excelDataList);

            //SQL拼接 columns部分
            List<Map<String, Object>> columns = excelMapper.findTmpTableColumns(realTableName);//所有字段名
            List<String> en_columns_list = new ArrayList<>();//数据库查出的临时表所有字段名
            en_columns_list.add("_id");//因为excelMapper.findTmpTableColumns()接口查询出来的字段名没有唯一Id “_id”，现加上
            for (Map<String, Object> map : columns) {
                String column_en_name = map.get("en_column").toString();//字段英文名
                en_columns_list.add(column_en_name);
            }
            en_columns_list.add("_batch_id");
            //System.out.println("en_columns_list\n"+en_columns_list);
            String columnSql = String.valueOf(en_columns_list).substring(1, String.valueOf(en_columns_list).length() - 1);
            //System.out.println("重新插入修改后的数据到对应所在的字段："+columnSql);
            //SQL拼接 values部分
            //String valuesSQL = joinSQL(excelDataList);
            String resultStr = "";
            String valuesSQL = "";//SQL语句拼接(和新增不一样，这里有batchId)
            for (List<String> list : excelDataList) {
                String str1 = "(";
                for (int i = 0; i < list.size(); i++) {
                    str1 += "'" + list.get(i) + "',";
                }
                str1 = str1.substring(0, str1.length() - 1);
                str1 += ",'" + batchId + "'),";
                resultStr += str1;
                valuesSQL = resultStr.substring(0, resultStr.length() - 1);
            }
            System.out.println("**拼values的SQL语句为↓↓:\n" + valuesSQL);
            //重新插入Excel中修改后的数据
//            excelMapper.insertModifiedData(tmpTableName, columnSql, valuesSQL);
            tempTableMapper.insertModifiedData(tmpTableName, columnSql, valuesSQL);

            //Excel中修改后的数据导入，后台进行验证
            Integer templateId = dataCollectService.getTemplateIdByBatchId(batchId);
            asyncExecutorValidService.ValidDatas(tmpTableName, Long.valueOf(templateId),batchId);

            //校验完成过后  更新信息（有效、无效数据量，以及验证进度）
            updateCollectImport( templateId, batchId);
        } else {
            //执行新增数据
            System.out.println("执行【新增Excel数据】。。。。。。。。。。。。。");
            Integer templateId = dataCollectService.getTemplateIdByBatchId(batchId);
            importExeclData(file, templateId, batchId);
            //校验完成后  更新信息（有效、无效数据量，以及验证进度）
            updateCollectImport( templateId, batchId);
        }
        FileUtil.deleteFile(tempFilePath);
        return jsonResult;
    }
    
}
