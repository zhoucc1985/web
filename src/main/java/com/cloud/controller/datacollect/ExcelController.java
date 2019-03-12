package com.cloud.controller.datacollect;

import com.cloud.common.utils.CommonUtils;
import com.cloud.service.datacollect.DataCollectService;
import com.cloud.service.datacollect.ExcelService;
import com.cloud.service.datacollection.DataCollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 数据采集反馈-Excel导入导出
 *
 * @author zengqh
 * @time 2019/01/10 15:42
 */
@Api(tags="数据采集反馈4",description = "Excel导入导出-ExcelController")
@RestController
@RequestMapping(value = "/datacollect/excel")
public class ExcelController {

    private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);
    @Autowired
    private DataCollectService dataCollectService;
    @Autowired
    private ExcelService excelService;

    /**
     * 1、导入Excel 数据入库临时表
     * @param file 上传文件
     * @param templateId 模板ID
     */
    @ApiOperation(value = "1、导入Excel&数据入库临时表--程涛--已完成（简单校验）",notes="导入Excel的数据到数据库中临时表")
    @RequestMapping(value = "/import/{templateId}",method = RequestMethod.POST)
    public Map<String,Object> importData(@RequestParam("file") MultipartFile file,
                                         @PathVariable(value = "templateId") Integer templateId){
        Map<String,Object> resultMap;
        try {
            resultMap = excelService.importExeclData(file,templateId);
        }catch (Exception e){
            logger.error("导入错误:"+e);
            resultMap = CommonUtils.getWarnResultMap("导入数据失败：");
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 继续导入 继续导入 两种情况：1.新增Excel数据 2.导入修改后的Excel数据
     * @param file 上传文件
     * @param batchId 批次ID
     */
    @ApiOperation(value = "继续导入--程涛--进行中",notes="在数据导入批次列表后点击“继续导入”")
    @RequestMapping(value = "/continueImportData/{batchId}",method = RequestMethod.POST)
    public Map<String,Object> continueImportData(@RequestParam("file") MultipartFile file,
                                                @PathVariable(value = "batchId") String batchId){
        Map<String,Object> resultMap;
        try {
            resultMap = excelService.continueImportData(file,batchId);
        }catch (Exception e){
            logger.error("导入错误:"+e);
            resultMap = CommonUtils.getWarnResultMap("导入数据失败：");
        }
        return resultMap;
    }

}
