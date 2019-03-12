package com.cloud.controller.reporttemplate;

import com.cloud.common.excption.BusinessException;
import com.cloud.common.utils.CommonUtils;
import com.cloud.service.reporttemplate.ReportTemplateService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 报告模板管理
 *
 * @program cloudIAS
 * @author: Pan Jianneng
 * @create: 2018/11/14
 */
@Api(tags="报告模板",description = "报告模板接口")
@RestController
@RequestMapping(value = "/api/report-template")
public class ReportTemplateController {
    private static final Logger logger = LoggerFactory.getLogger(ReportTemplateController.class);

    @Autowired
    private ReportTemplateService reportTemplateService;

    /**
     * 导入报表字段数据测试接口
     * @author Pan Jianneng
     * @date 2018/11/14
     * @param file 文件
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @ApiOperation(value = "测试接口，不要尝试使用",notes="测试接口，导入Excel中报表字段数据测试-done")
    @RequestMapping(value = "/imoort-report-template-filed-data-test",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> testImportData(@RequestParam("file") MultipartFile file) throws BusinessException{
        Map<String,Object> resultMap = CommonUtils.getSucResultMap();
        if(true){
            return CommonUtils.getErrorResultMap("不要尝试使用！");
        }
        try {
            resultMap = reportTemplateService.createReportDataTest(file);
        }catch (Exception e){
            logger.error("读取错误:"+e.getLocalizedMessage());
            resultMap = CommonUtils.getErrorResultMap("读取数据失败："+e.getMessage());
        }
        return resultMap;
    }

    /**
     * 通过报告模板ID查询模板字段数据，并导出Excel
     * @author Pan Jianneng
     * @date 2018/11/15
     * @param response
     * @param reportTemplateId
     * @return void
     */
    @ApiOperation(value = "下载报告模板字段数据模板",notes = "报告模板字段数据模板下载-done")
    @RequestMapping(value = "/download/{batchId}/{reportTemplateId}",method = RequestMethod.GET)
    public void downloadByReportTemplateId(HttpServletResponse response,@PathVariable(value = "batchId") Long batchId, @PathVariable(value = "reportTemplateId") Long reportTemplateId) {
        reportTemplateService.downloadReportTemplateFiledDataExcel(response,batchId,reportTemplateId);
    }

    /**
     * 分页查询报告模板列表
     * @author Pan Jianneng
     * @date 2018/11/24 12:04 PM
     * @param searchContent
     * @param pageInfo
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @ApiOperation(value = "分页查询报告模板列表",notes = "查询报告模板列表")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Map<String,Object> findListByPage(@RequestParam(value = "searchContent",required = false) String searchContent, @ModelAttribute PageInfo pageInfo){
        return CommonUtils.getSucResultMap(reportTemplateService.findReportTemplateList(searchContent,pageInfo));
    }

    /**
     * 查看报告模板详情
     * @author Pan Jianneng
     * @date 2018/11/24 2:20 PM
     * @param
     * @return
    */
    @ApiOperation(value = "查询报告模板详情",notes = "查看报告模板详情")
    @RequestMapping(value = "/info/{reportTemplateId}",method = RequestMethod.GET)
    public Map<String,Object> findInfoById(@PathVariable(value = "reportTemplateId") Long reportTemplateId){
        return CommonUtils.getSucResultMap(reportTemplateService.findReportTemplateInfo(reportTemplateId));
    }
}

