package com.cloud.controller.datacollect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.common.qo.CheckQo;
import com.cloud.common.utils.CommonUtils;
import com.cloud.common.vo.datacollect.CollectBatchSearchVo;
import com.cloud.service.datacollect.AsyncExecutorValidService;
import com.cloud.service.datacollect.DataCollectService;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 数据采集(批次情况列表)
 * @author zengqh
 */
@Api(tags="数据采集反馈1",description = "数据采集(批次情况列表)-DataCollectController")
@RestController
@RequestMapping(value = "/datacollect")
public class DataCollectController {
    private static final Logger logger = LoggerFactory.getLogger(DataCollectController.class);
    @Resource
    private DataCollectService dataCollectService;
    /**
     * 导入数据列表批次查询
     * @param collectBatchSearchVo 查询信息 包含分页信息
     */
    @ApiOperation(value="导入批次列表查询-琼海-完成",notes = "查询已经导入的数据列表（批次，数据有/无效性，校验进度）")
    @RequestMapping(value = "/exportDataList",method = RequestMethod.POST)
    public Map<String,Object> exportDataList(@RequestBody CollectBatchSearchVo collectBatchSearchVo){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            PageInfo pageInfo = dataCollectService.queryPage(collectBatchSearchVo);
            return CommonUtils.getSucResultMap(pageInfo);
        }catch(Exception e){
            logger.error("导入批次列表查询出错:" + e);
            resultMap = CommonUtils.getErrorResultMap("查询模板结果列表失败"+e.getMessage());
        }

        return resultMap;
    }
    /**
     * 删除当前批次数据（删除模板或入库的批次）
     */
    @ApiOperation(value="删除当前批次的数据-对接完成",notes = "删除当前批次的数据")
    @RequestMapping(value = "/deleteBatchData",method = RequestMethod.DELETE)
    public Map<String,Object> deleteBatchData(@RequestParam("batchId") String batchId){
        try{
              dataCollectService.deleteBatchData(batchId);
             return CommonUtils.getSucResultMap();
        }catch(Exception e){
            logger.error("删除当前批次数据出错了:" + e);
            e.printStackTrace();
            return  CommonUtils.getErrorResultMap("执行删除当前批次数据失败"+e.getMessage());
        }
    }

    /**
     * 完成当前批次导入 数据写到真实表
     */
    @ApiOperation(value="完成当前批次导入数据写到真实表",notes = "完成当前批次导入 数据写到真实表")
    @RequestMapping(value = "/saveDateToRealTable",method = RequestMethod.POST)
    public Map<String,Object> saveDateToRealTable(@RequestParam("batchId") String batchId){
        try{
            dataCollectService.saveDateToRealTable(batchId);
            return CommonUtils.getSucResultMap();
        }catch(Exception e){
            logger.error("导入当前批次数据到真实表失败:" + e);
            return  CommonUtils.getErrorResultMap("导入当前批次数据到真实表失败");
        }
    }

    @ApiOperation(value="查询数据库收集校验详情列表",notes = "查询数据库收集校验详情列表")
    @RequestMapping(value = "/showCollectCheckList",method = RequestMethod.POST)
    public Map<String,Object> showCollectCheckList(@RequestBody CheckQo qo){
        try{
        	PageInfo pageInfo = dataCollectService.showCollectCheckList(qo);
            return CommonUtils.getSucResultMap(pageInfo);
        }catch(Exception e){
            logger.error("查询数据库收集校验详情列表:" + e);
            return  CommonUtils.getErrorResultMap("查询数据库收集校验详情列表失败");
        }
    }

    @ApiOperation(value="数据库收集校验详情列表--查看详情",notes = "数据库收集校验详情列表--查看详情")
    @RequestMapping(value = "/showCollectCheckListDetails",method = RequestMethod.POST)
    public Map<String,Object> showCollectCheckListDetails(@RequestParam("templateId") Integer templateId){
        try{
            Map<String,Object> map= dataCollectService.showCollectCheckListDetails(templateId);
            return CommonUtils.getSucResultMap(map);
        }catch(Exception e){
            logger.error("查询数据库收集校验详情列表:" + e);
            return  CommonUtils.getErrorResultMap("查询数据库收集校验详情列表失败"+e.getMessage());
        }
    }

    @ApiOperation(value="数据库收集校验详情列表--导出报告",notes = "数据库收集校验详情列表--导出报告")
    @RequestMapping(value = "/export",method = RequestMethod.GET)
    public Map<String,Object> export(@RequestHeader(value = "User-Agent",required = false) String userAgent, HttpServletResponse response){
        try{
            dataCollectService.export(userAgent,response);
            return CommonUtils.getSucResultMap();
        }catch(Exception e){
            logger.error("导出报告失败:" + e);
            return  CommonUtils.getErrorResultMap("导出报告失败:"+e.getMessage());
        }
    }

    @ApiOperation(value="数据库收集校验详情列表--查询下拉框",notes = "数据库收集校验详情列表--查询下拉框")
    @RequestMapping(value = "/showDropDownBox",method = RequestMethod.POST)
    public Map<String,Object> showDropDownBox(){
        try{
            Map<String,Object> map = dataCollectService.showDropDownBox();
            return CommonUtils.getSucResultMap(map);
        }catch(Exception e){
            logger.error("导出报告失败:" + e);
            return  CommonUtils.getErrorResultMap("导出报告失败:"+e.getMessage());
        }
    }

}
