package com.cloud.controller.excelexport;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.common.utils.CommonUtils;
import com.cloud.common.utils.FileUtil;
import com.cloud.common.utils.MyExcelUtil;
import com.cloud.entity.datamanagement.DBDatasource;
import com.cloud.entity.datamanagement.Ftpdatasource;
import com.cloud.service.datamanagement.DBManagementService;
import com.cloud.service.datamanagement.FtpManagementService;
import com.cloud.service.excelstat.ExcelStatService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Excel操作
 *
 * @author zhuJT
 * @time 2019/01/16
 */
@Api(tags = "Excel操作", description = "Excel操作")
@RestController
@RequestMapping(value = "/excel")
public class ExcelExportController {

    private static final Logger logger = LoggerFactory.getLogger(ExcelExportController.class);

    @Autowired
    private ExcelStatService excelStatService;
    @Autowired
    private FtpManagementService ftpManagementService;
    @Autowired
    private DBManagementService dbManagementService;


    
    @ApiOperation(value = "ftp数据导出", notes = "ftp数据导出")
    @RequestMapping(value = "/ftpExport", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> ftpExcelExport(@RequestHeader(value = "UserAgent", required = false) String userAgent,
                                              HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = CommonUtils.getSucResultMap();
        try {
            excelStatService.ftpExcelExport(request, response, userAgent);
            return resultMap;
        } catch (Exception e) {
            logger.error("导入错误:" + e);
            resultMap = CommonUtils.getWarnResultMap("导入数据失败：" + e.getMessage());
        }
        return resultMap;
    }
    

    @ApiOperation(value = "导入FTP模板")
    @RequestMapping(value = "importFTP", method = RequestMethod.POST)
    public Map<String, Object> importFtp(@RequestParam(value = "file") MultipartFile file) {
        Map<String, Object> resultMap = CommonUtils.getSucResultMap("正在处理导入的文件，请稍后，过程中您可以做其他操作。");
        String filePath = "";
        try {
            filePath = ftpManagementService.importFtp(file);
            //解析Excel
            List<Ftpdatasource> list = MyExcelUtil.readFtpExcel(filePath);
            ftpManagementService.asyncImportFtp(list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("导入模板，生成报告失败", e.getMessage());
            resultMap = CommonUtils.getErrorResultMap("生成报告失败" + e.getMessage());
        }

        if (!"".equals(filePath)) {
            //删除临时文件
            FileUtil.deleteFile(filePath);
        }

        return resultMap;
    }

    
    @ApiOperation(value = "DB数据导出", notes = "DB数据导出")
    @RequestMapping(value = "/DBExport", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> DBExcelExport(@RequestHeader(value = "UserAgent", required = false) String userAgent,
                                             HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = CommonUtils.getSucResultMap();
        try {
            excelStatService.DBExcelExport(request, response, userAgent);
            return resultMap;
        } catch (Exception e) {
            logger.error("导出错误:" + e);
            resultMap = CommonUtils.getWarnResultMap("导出数据失败：" + e.getMessage());
        }
        return resultMap;
    }
    

    @ApiOperation(value = "导入DB模板")
    @RequestMapping(value = "importDB", method = RequestMethod.POST)
    public Map<String, Object> importDB(@RequestParam(value = "file") MultipartFile file) {
        Map<String, Object> resultMap = CommonUtils.getSucResultMap("正在处理导入的文件，请稍后，过程中您可以做其他操作。");
        String filePath = "";
        try {
            filePath = dbManagementService.importDB(file);
            //解析Excel
            List<DBDatasource> list = MyExcelUtil.readDBExcel(filePath);
            dbManagementService.asyncInsertDB(list);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("导入模板，生成报告失败", e.getMessage());
            resultMap = CommonUtils.getErrorResultMap("生成报告失败" + e.getMessage());
        }

        if (!"".equals(filePath)) {
            //删除临时文件
            FileUtil.deleteFile(filePath);
        }
        return resultMap;
    }

    @ApiOperation(value = "下载DB模板")
    @RequestMapping(value = "/downloadDBExcel", method = RequestMethod.GET)
    public void downloadExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream("/templates/DB数据源模板.xlsx");
        byte[] fileData = MyExcelUtil.input2byte(inputStream);
        MyExcelUtil.downloadFile(response, request, "DB数据源模板.xlsx", fileData);
    }

    @ApiOperation(value = "下载FTP模板")
    @RequestMapping(value = "/downloadFTPExcel", method = RequestMethod.GET)
    public void downloadFTPExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream("/templates/FTP数据源模板.xlsx");
        byte[] fileData = MyExcelUtil.input2byte(inputStream);
        MyExcelUtil.downloadFile(response, request, "FTP数据源模板.xlsx", fileData);
    }
}
