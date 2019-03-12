package com.cloud.service.datacollection;

import com.cloud.common.enums.CollectionBatchStatusEnum;
import com.cloud.common.excption.BusinessException;
import com.cloud.common.utils.*;
import com.cloud.common.vo.ShiroUser;
import com.cloud.common.vo.datacollection.CollectionDataErrorObjVO;
import com.cloud.entity.datacollection.CollectionBatch;
import com.cloud.entity.datacollection.CollectionDataSource;
import com.cloud.entity.datacollection.CollectionDataStandard;
import com.cloud.entity.datacollection.ReportTemplatePageField;
import com.cloud.entity.report.Report;
import com.cloud.mapper.master.datacollection.CollectionBatchMapper;
import com.cloud.mapper.master.datacollection.CollectionDataSourceMapper;
import com.cloud.mapper.master.datacollection.CollectionDataStandardMapper;
import com.cloud.mapper.master.datacollection.ReportTemplatePageFieldMapper;
import com.cloud.mapper.master.report.ReportMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * 数据采集业务处理类
 *
 * @author Pan jianneng
 * @time 2018/10/21 15:50
 */
 @Service
public class DataCollectionService {

    private static final Logger logger = LoggerFactory.getLogger(DataCollectionService.class);

    @Autowired
    private AsyncExecutorCommonService asyncExecutorService;

    @Autowired
    private CollectionDataCommonService commonService;

    @Resource
    private CollectionBatchMapper collectionBatchMapper;

    @Resource
    private CollectionDataSourceMapper sourceMapper;

    @Resource
    private ReportTemplatePageFieldMapper fieldMapper;

    @Resource
    private CollectionDataStandardMapper standardMapper;

    @Resource
    private ReportMapper reportMapper;

    /**
     * 文件上传临时保存目录地址
     */
    @Value("${data-collection-upload-file-path}")
    private String filePath;

    /**
     *
     * 这里异步处理
     *
     * @param file
     * @param batchId
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @auther: Pan jianneng
     * @date: 2018/10/21 21:49
     */
    public Map<String,Object> importExeclData(MultipartFile file,Long batchId) {
        if(ObjectUtils.isEmptyString(file.getOriginalFilename())){
            throw new BusinessException("请选择上传文件");
        }
        if(ObjectUtils.isNullObject(collectionBatchMapper.selectByPrimaryKey(batchId))){
            throw new BusinessException("报告模板批次不存在");
        }
        Map<String,Object> jsonResult = CommonUtils.getWarnResultMap("正在处理导入的文件，请稍后，过程中您可以做其他操作。");
        //临时保存数据文档
        String tempFilePath = saveExcelTemp(file,batchId);
        ShiroUser shiroUser = UserUtils.getInstance().getCurrentlyUserInfo();
        asyncExecutorService.syncHandleData(tempFilePath,batchId,shiroUser);
        //修改批次状态为数据导入中
        collectionBatchMapper.modifyStatusByIdAndStatus(batchId,CollectionBatchStatusEnum.IMPORTING.toString());
        return jsonResult;
    }

    @Async("asyncExecutor")
    public void test1(String filePath,String fileName){
        logger.info("start read"+fileName+"-"+System.currentTimeMillis());
        Long startTime = System.currentTimeMillis();
        List<String[]> readerExcelList = new ArrayList<String[]>();
        try {
            readerExcelList = ExcelCovertToCsvAndReader.readExcel(
                            filePath, 10);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OpenXML4JException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        List<String[]> trimList = new ArrayList<String[]>();
        readerExcelList.forEach(strings -> {
            String[] str = strings;
            for(int i=0;i<str.length;i++){
                if(ObjectUtils.isNotNullObject(str[i])){
                    try {
                        str[i] = ObjectUtils.trimFirstAndLastChar(str[i],'"');
                    } catch (Exception e) {
                        logger.error("读取数据失败："+e.getMessage());
                        throw  new BusinessException("导入数据失败");
                    }
                }else{
                    str[i] = "";
                }
            }
            trimList.add(str);
        });
        Long end = System.currentTimeMillis();
        Long hms = (end-startTime)/1000%60;
        logger.info("读取到【"+fileName+"】文件中共有["+trimList.size()+"]条数据-耗时:"+hms);
        logger.info("end read"+fileName+"-"+System.currentTimeMillis());
    }

    /**
     * 临时保存导入的文档，返回保存目录
     * @author Pan Jianneng
     * @date 2018/11/16 11:28 AM
     * @params [file, batchId]
     * @return java.lang.String
     */
    private String saveExcelTemp(MultipartFile file,Long batchId){
        ShiroUser userInfo = UserUtils.getInstance().getCurrentlyUserInfo();
        //导入数据临时文件保存位置
        String tempSavePath = filePath+"/";
        if(ObjectUtils.isNotNullObject(userInfo)){
            tempSavePath += userInfo.loginName+"/"+userInfo.getOrgId()+"/"+batchId+"/"+System.currentTimeMillis()+"/";
        }else{
            tempSavePath += System.currentTimeMillis()+"/";
        }
        String fileName = file.getOriginalFilename();
        //路径校验，恶心，直接写是个魔法值
        String index = "\\";
        if(fileName.indexOf(index) != -1){
            fileName = fileName.substring(fileName.lastIndexOf("\\"));
        }
        File targetFile = new File(tempSavePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(tempSavePath+fileName);
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempSavePath+fileName;
    }

    /**
     * 导出查询校验错误列表
     * @author Pan Jianneng
     * @date 2018/11/23 1:57 PM
     * @param batchId
     * @return java.util.List<java.util.Map<java.lang.String,com.cloud.common.vo.datacollection.CollectionDataErrorObjVO>>
     */
    public List<Map<String,CollectionDataErrorObjVO>> getCollectionDataSourceErrorsByBatchId(Long batchId){
        List<Map<String,CollectionDataErrorObjVO>> result = new ArrayList<Map<String,CollectionDataErrorObjVO>>();
        List<CollectionDataSource> errorSources =  sourceMapper.findErrorsByBatchId(batchId);
        if(ObjectUtils.isNotEmptyList(errorSources)){
            result = CollectionDataVoConvertUtils.convertToErrorListByList(
                    errorSources);
        }
        return result;
    }

    /**
     * 分页查询错误列表
     * @author Pan Jianneng
     * @date 2018/11/23 2:25 PM
     * @param batchId
     * @param pageInfo
     * @return com.github.pagehelper.PageInfo<java.util.Map<java.lang.String,com.cloud.common.vo.datacollection.CollectionDataErrorObjVO>>
     */
    public PageInfo<Map<String,CollectionDataErrorObjVO>> pageGetSourceErrorsByBatchId(Long batchId,PageInfo pageInfo){
        List<Map<String,CollectionDataErrorObjVO>> result = new ArrayList<Map<String,CollectionDataErrorObjVO>>();
        PageHelper.offsetPage(pageInfo.getStartRow(), pageInfo.getPageSize());
        PageInfo<CollectionDataSource> pageInfos =  new PageInfo<CollectionDataSource>(
                sourceMapper.findErrorsByBatchId(batchId));
        List<CollectionDataSource> errorSources = pageInfos.getList();
        if(ObjectUtils.isNotEmptyList(errorSources)){
            result = CollectionDataVoConvertUtils.convertToErrorListByList(errorSources);
        }
        PageInfo<Map<String,CollectionDataErrorObjVO>> resultPages = new PageInfo<Map<String,CollectionDataErrorObjVO>>(result);
        resultPages.setPageNum(pageInfos.getPageNum());
        resultPages.setPageSize(pageInfos.getPageSize());
        resultPages.setTotal(pageInfos.getTotal());
        return resultPages;
    }


    /**
     * 根据对象进行错误数据修改
     * @author Pan Jianneng
     * @date 2018/11/23 2:08 PM
     * @param entity
     * @return void
     */
    public Map<String,Object> modifyCollectionDataSourceErrorByEntity(CollectionDataSource entity){
        CollectionDataSource old = sourceMapper.selectByPrimaryKey(entity.getId());
        if(ObjectUtils.isNullObject(old)){
            return CommonUtils.getErrorResultMap("错误数据已被修复，请选择修改其他错误的数据");
        }

        CollectionDataSource checkSourceData = commonService.checkExcelDataSingle(entity);
        if(!checkSourceData.isError()){
            CollectionDataStandard standard = commonService.convertSingle(checkSourceData);
            if(ObjectUtils.isNotNullObject(standard)){
                List<CollectionDataStandard> list = new ArrayList<CollectionDataStandard>();
                list.add(standard);
                standardMapper.insertOrUpdateStandardByBatch(list);
                sourceMapper.deleteByPrimaryKey(entity.getId());
                Report report = reportMapper.findByBatchId(entity.getBatchId());
                if(ObjectUtils.isNotNullObject(report)){
                    reportMapper.updateGenerateAgainById(true,report.getId());
                }
            }
            return CommonUtils.getSucResultMap("保存成功");
        }
        List<CollectionDataSource> checkSourceDataTempList = new ArrayList<CollectionDataSource>();
        checkSourceDataTempList.add(checkSourceData);
        List<Map<String,CollectionDataErrorObjVO>> showSourceDataList = CollectionDataVoConvertUtils.
                convertToErrorListByList(checkSourceDataTempList);
        StringBuilder sbMsg = new StringBuilder();
        if(ObjectUtils.isNotEmptyList(showSourceDataList)){
            Map<String,CollectionDataErrorObjVO> map = showSourceDataList.get(0);
            if(ObjectUtils.isNotNullObject(map)){
                for(String key:map.keySet()){
                    Object valueNull = map.get(key);
                    if(ObjectUtils.isNotNullObject(valueNull)){
                        Object className = valueNull.getClass().getName();
                        if(className.equals(CollectionDataErrorObjVO.class.getName())){
                            CollectionDataErrorObjVO vo = (CollectionDataErrorObjVO) map.get(key);
                            sbMsg.append(vo.getMsg());
                            sbMsg.append("<br>");
                        }
                    }
                }
            }
        }
        return CommonUtils.getErrorResultMap(sbMsg.toString());
    }

    /**
     * 导出错误列表
     * @author Pan Jianneng
     * @date 2018/11/29 2:48 PM
     * @params [batchId, response]
     * @return void
     */
    public void exportErrorList(Long batchId, HttpServletResponse response){
        CollectionBatch batch = collectionBatchMapper.selectByPrimaryKey(batchId);
        if(ObjectUtils.isNullObject(batch)){
            throw new BusinessException("没有找到错误数据所属批次");
        }
        List<Map<String,CollectionDataErrorObjVO>> errorList = getCollectionDataSourceErrorsByBatchId(batch.getId());
        List<ReportTemplatePageField> rtFields = fieldMapper.findByReportTemplateId(batch.getRtId());
        if(ObjectUtils.isEmptyList(rtFields)){
            throw new BusinessException("获取表头失败");
        }
        LinkedHashMap<String, String> header = new LinkedHashMap<String, String>();
        rtFields.forEach(rtField -> {
            header.put(rtField.getCode(),rtField.getName());
        });
        try {
            // 声明一个工作薄
            XSSFWorkbook workbook = new XSSFWorkbook();
            //所有单元格上锁
            ExcelUtil.lockStyle(workbook);
            // 生成一个表格
            XSSFSheet sheet = workbook.createSheet("迎新数据");
            //设置要导出的文件的名字
            String fileName = "迎新数据-"+batch.getBatchNo()+"-错误数据"  + ".xlsx";
            // 设置表格默认列宽度为15个字节
            sheet.setDefaultColumnWidth(15);
            // 声明一个画图的顶级管理器
            XSSFDrawing patriarch = sheet.createDrawingPatriarch();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            response.flushBuffer();
            //设置隐藏列，保存批次ID
            /// TODO ExcelUtil.setNoneCell(workbook,sheet,String.valueOf(batch.getId()));
            ExcelUtil.transform(workbook,sheet,patriarch,header,errorList,response.getOutputStream());
        } catch (Exception e) {
            logger.error("导出错误列表失败："+e.getMessage());
            e.printStackTrace();
        }
    }


//    public void deleteData(Date startTime,Date endTime,String name,String id){
//        collectionBatchMapper.deleteByPrimaryKey(startTime,endTime,name,id);
//    }
}
