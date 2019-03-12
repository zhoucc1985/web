package com.cloud.controller.qualityReport;

import com.cloud.common.utils.CommonUtils;
import com.cloud.entity.datamanagement.TableInfo;
import com.cloud.entity.vo.PageParam;
import com.cloud.entity.vo.qualityReport.*;
import com.cloud.service.qualityReport.QualityReportService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 数据质量报告 controller
 * @Author daituo
 * @Date 2019-1-17
 **/
@Api(tags = "数据质量报告", description = "数据质量报告")
@RestController
@RequestMapping("/quality/report")
public class QualityReportController {

    private static final Logger LOG = LoggerFactory.getLogger(QualityReportController.class);

    @Autowired
    private QualityReportService qualityReportService;



    @ApiOperation(value = "按系统分组查询所有系统下的所有表--戴拖", notes = "按系统分组查询系统下的所有表--戴拖")
    @ApiImplicitParam(name = "token", value = "token", dataType = "String", paramType = "query")
    @GetMapping(value = "/list/tables")
    public Map<String,Object> getAllTablesGroupBySystem() {
        List<TableMenuVo> tableMenuVoList = qualityReportService.getAllTablesGroupBySystem();
        return CommonUtils.getSucResultMap(tableMenuVoList);
    }



    @ApiOperation(value = "查询指定系统下的所有表--戴拖", notes = "查询指定系统下的所有表--戴拖")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "id", value = "数据库数据源Id", dataType = "long", paramType = "path", required = true)
    })
    @GetMapping(value = "/list/tables/{id}")
    public Map<String, Object> getAllTablesByDataSourceId(@ModelAttribute PageParam pageParam, @PathVariable("id") Long id,
                                                          @RequestParam(value = "sort", required = false) Long sort) {
        PageInfo<TableInfo> tableInfoPage = qualityReportService.getAllTablesByDataSourceId(pageParam, id, sort);
        return CommonUtils.getSucResultMap(tableInfoPage);
    }



    @ApiOperation(value = "查询数据库校验报告--戴拖", notes = "查询数据库校验报告--戴拖")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "token", value = "token", dataType = "String", paramType = "query"),
    })
    @GetMapping(value = "/db")
    public Map<String,Object> getDbQualityReport(@ModelAttribute PageParam pageParam) {
        try {
            PageInfo<DbQualityReportVo> dbQualityReportVoPage = qualityReportService.getDbQualityReport(pageParam);
            return CommonUtils.getSucResultMap(dbQualityReportVoPage);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("查询数据库校验报告出错", e);
            return CommonUtils.getErrorResultMap();
        }
    }



    @ApiOperation(value = "查询指定table下的所有字段详情--戴拖", notes = "查询指定table下的所有字段详情--戴拖")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "tableId", value = "表Id", dataType = "long", paramType = "path", required = true)
    })
    @GetMapping(value = "/list/fields/{tableId}")
    public Map<String, Object> getFieldsByTableId(@PathVariable("tableId") Long tableId, @ModelAttribute PageParam pageParam,
                                                  @RequestParam(value = "sort",required = false) Long sort) {
        PageInfo<FieldDetailVo> fieldDetailVoPage = qualityReportService.getFieldsByTableId(tableId, pageParam,sort);
        return CommonUtils.getSucResultMap(fieldDetailVoPage);
    }



    @ApiOperation(value = "查询FTP数据校验报告--戴拖", notes = "查询FTP数据校验报告--戴拖")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "token", value = "token", dataType = "String", paramType = "query"),
    })
    @GetMapping(value = "/ftp")
    public Map<String,Object> getFtpQualityReport(@ModelAttribute PageParam pageParam) {
        PageInfo<FtpQualityReportVo> ftpQualityReportVoPage = qualityReportService.getFtpQualityReport(pageParam);
        return CommonUtils.getSucResultMap(ftpQualityReportVoPage);
    }




    @ApiOperation(value = "查询指定FTP源下的质量详情列表--戴拖", notes = "查询指定FTP源下的质量详情列表--戴拖")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "token", value = "token", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "ftpId", value = "ftpId", dataType = "long", paramType = "path"),
    })
    @GetMapping(value = "/list/ftp/{ftpId}")
    public Map<String,Object> getFtps(@ModelAttribute PageParam pageParam,@PathVariable("ftpId") Long ftpId) {
        PageInfo<FtpDetailVo> ftpDetailVoPage = qualityReportService.getFtps(pageParam,ftpId);
        return CommonUtils.getSucResultMap(ftpDetailVoPage);
    }




    @ApiOperation(value = "导出报告--戴拖", notes = "导出报告--戴拖")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "token", value = "token", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "type", value = "报告类型【D-数据库 F-FTP】", dataType = "String", paramType = "query"),
    })
    @GetMapping(value = "/export")
    public Map<String,Object> export(@RequestHeader("User-Agent") String userAgent,HttpServletResponse response, String type) {
        try {
            qualityReportService.export(userAgent,response,type);
            return CommonUtils.getSucResultMap();
        } catch (Exception e) {
            LOG.error("QualityReportController.export has exception:{}",e);
            return CommonUtils.getErrorResultMap("导出异常"+e.getMessage());
        }
    }




    @ApiOperation(value = "指定表的数据预览，默认查询10条数据--戴拖", notes = "指定表的数据预览，默认查询10条数据--戴拖")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "token", value = "token", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "tableId", value = "表Id", dataType = "long", paramType = "path"),
    })
    @GetMapping(value = "/preview/{tableId}")
    public Map<String,Object> dataPreview(@PathVariable("tableId") Long tableId) {
        List<Map<String,Object>> dynamicData = qualityReportService.dataPreview(tableId,true);
        return CommonUtils.getSucResultMap(dynamicData);
    }




    @ApiOperation(value = "导出指定表的预览数据详情--戴拖", notes = "导出指定表的预览数据详情--戴拖")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "token", value = "token", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "tableId", value = "表Id", dataType = "long", paramType = "path"),
    })
    @GetMapping(value = "/data/export/{tableId}")
    public Map<String,Object> dataPreviewExport(@PathVariable("tableId") Long tableId,@RequestHeader("User-Agent") String userAgent,
                                                HttpServletResponse response) {
        try {
            qualityReportService.dataPreviewExport(userAgent,response,tableId);
            return CommonUtils.getSucResultMap();
        } catch (Exception e) {
            LOG.error("QualityReportController.dataPreviewExport() has exception:{}",e);
            return CommonUtils.getErrorResultMap("导出异常");
        }
    }
    /**
     * 查询数据库下所有的空表List
     * @param id 数据库Id
     * @return
             */
    @ApiOperation(value = "查询数据库下所有的空表名称List -- 程涛 -- 已完成")
    @ApiImplicitParam(name="id",value = "数据库Id",paramType = "query",dataType = "Integer")
    @GetMapping(value = "/viewEmptyTableList")
    public Map<String,Object> viewEmptyTableList(@RequestParam("id") Integer id){
        try {
            return CommonUtils.getSucResultMap(qualityReportService.viewEmptyTableList(id));
        } catch (Exception e) {
            LOG.error("QualityReportController.viewEmptyTableList() has exception:{}"+e);
            //e.printStackTrace();
            return CommonUtils.getErrorResultMap("查看空表list出错了");
        }
    }


    @ApiOperation(value = "导出数据库下所有空表名称 -- 程涛 -- 已完成")
    @ApiImplicitParam(name="id",value = "数据库Id",paramType = "query",dataType = "Integer")
    @RequestMapping(value = "/exportEmptyTableList")
    public Map<String,Object> exportEmptyTableList(HttpServletResponse response,
                                                   HttpServletRequest request,
                                                   @RequestParam("id") Integer id){
        try {
            qualityReportService.exportEmptyTableList(response,request,id);
            return CommonUtils.getSucResultMap();
        } catch (Exception e) {
            LOG.error("导出空表list出错了"+e.getMessage());
            return CommonUtils.getErrorResultMap("导出空表出错");
        }
    }


    /**
     * 查询FTP下所有的空表List
     * @param id ftpId
     * @return
     */
    @ApiOperation(value = "查询FTP下所有的空表List -- 朱璟韬")
    @ApiImplicitParam(name="id",value = "FTPId",paramType = "query",dataType = "Integer")
    @GetMapping(value = "/viewEmptyFTPTableList")
    public Map<String,Object> viewEmptyFTPTableList(@RequestParam("id") Integer id){
        try {
            return CommonUtils.getSucResultMap(qualityReportService.viewEmptyFTPTableList(id));
        } catch (Exception e) {
            LOG.error("QualityReportController.viewEmptyTableList() has exception:{}"+e);
            //e.printStackTrace();
            return CommonUtils.getErrorResultMap("查看空表list出错了");
        }
    }

    @ApiOperation(value = "数据库详情单表导出报告--朱璟韬")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "该条数据的数据源id", dataType = "Integer", paramType = "query")
    })
    @GetMapping(value = "/exportBySystemId")
    public Map<String,Object> exportBySystemId(@RequestHeader("User-Agent") String userAgent,HttpServletResponse response, Long id) {
        try {
            qualityReportService.exportBySystemId(userAgent,response,id);
            return CommonUtils.getSucResultMap();
        } catch (Exception e) {
            LOG.error("QualityReportController.export has exception:{}",e);
            return CommonUtils.getErrorResultMap("导出异常");
        }
    }

    //@Resource
    //private testMapper testMapper;
    //
    //@ApiOperation(value = "测试second数据源--戴拖", notes = "查询数据库校验报告--戴拖")
    //@GetMapping(value = "/second")
    //public Map<String,Object> test(@ModelAttribute PageParam pageParam) {
    //    PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
    //    testExample testExample = new testExample();
    //    List<test> tests = testMapper.selectByExample(testExample);
    //    PageInfo<test> testPageInfo = new PageInfo<>(tests);
    //    return CommonUtils.getSucResultMap(testPageInfo);
    //}
    @ApiOperation(value = "同步数据库校验详情")
    @ApiImplicitParam(name="id",value = "DB数据源Id",paramType = "query",dataType = "Integer")
    @RequestMapping(value = "/synchroDataBase")
    public Map<String,Object> synchroDataBase(@RequestParam("id") Integer id){
        try {
        	qualityReportService.synchroDataBase(id);
            return CommonUtils.getSucResultMap();
        } catch (Exception e) {
            LOG.error("同步数据库校验详情出错"+e.getMessage());
            return CommonUtils.getErrorResultMap("同步数据库校验详情出错");
        }
    }
}
