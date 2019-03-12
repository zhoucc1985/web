package com.cloud.controller.datacollection;

import com.cloud.common.utils.CommonUtils;
import com.cloud.common.utils.ObjectUtils;
import com.cloud.common.vo.datacollection.CollectionBatchSearchVo;
import com.cloud.entity.datacollection.CollectionBatch;
import com.cloud.entity.datacollection.CollectionDataSource;
import com.cloud.service.datacollection.CollectionBatchService;
import com.cloud.service.datacollection.DataCollectionService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 数据采集
 *
 * @author Pan jianneng
 * @time 2018/10/21 15:42
 */
@Api(tags="数据采集业务-old",description = "数据采集业务")
@RestController
@RequestMapping(value = "/api/data-collection")
public class DataCollectionController {

    private static final Logger logger = LoggerFactory.getLogger(DataCollectionController.class);

    @Resource
    private DataCollectionService dataCollectionService;

    @Resource
    private CollectionBatchService collectionBatchService;

    /**
     *
     * 根据批次ID导入数据，并注册发送消息
     *
     * @param file
     * @param request
     * @param response
     * @param batchId
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @auther: Pan jianneng
     * @date: 2018/10/21 15:50
     */
    @ApiOperation(value = "导入、继续导入字段数据",notes="导入字段模板数据")
    @RequestMapping(value = "/import/{batchId}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> importData(@RequestParam("file") MultipartFile file,@PathVariable(value = "batchId") Long batchId){
        Map<String,Object> resultMap = CommonUtils.getSucResultMap();
        try {
            resultMap = dataCollectionService.importExeclData(file,batchId);
        }catch (Exception e){
            logger.error("导入错误:"+e);
            resultMap = CommonUtils.getWarnResultMap("导入数据失败："+e.getMessage());
        }
        return resultMap;
    }

    /**
     * 导出错误列表
     * @author Pan Jianneng
     * @date 2018/11/29 6:32 PM
     * @params [response, batchId]
     * @return void
     */
    @ApiOperation(value = "导出错误列表数据",notes = "根据批次ID导出当前批次错误数据列表")
    @RequestMapping(value = "/fail-list/export/{batchId}",method = RequestMethod.GET)
    public void downloadByReportTemplateId(HttpServletResponse response,@PathVariable(value = "batchId") Long batchId) {
        dataCollectionService.exportErrorList(batchId,response);
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> test(HttpServletResponse response){
        Map<String,Object> resultMap = CommonUtils.getSucResultMap();
        dataCollectionService.exportErrorList(4L,response);
        return resultMap;
    }

    /**
     * 查询数据采集列表
     * @param collectionBatchSearchVo 查询信息，包含分页信息
     * @author lq
     * @date 2018年11月14日 下午5:33:31
     * @return
     */
    @ApiOperation(value = " 查询数据采集列表")
    @RequestMapping(value="/batch",method= RequestMethod.POST)
    public Map<String, Object> findCollectBatch(@RequestBody CollectionBatchSearchVo collectionBatchSearchVo){
        try {
            PageInfo<CollectionBatch> batchList = collectionBatchService.findCollectionBatchList(collectionBatchSearchVo);
            return CommonUtils.getSucResultMap(batchList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询数据采集列表异常");
            return CommonUtils.getErrorResultMap("查询数据采集列表异常");
        }
    }



    /**
     * 新增采集记录，只有学校人员才可以操作
     * @param templateId   采集记录模板信息ID
     */
    @ApiOperation(value = "新增采集记录,只有学校才可以操作！！")
    @RequestMapping(value="/template/{templateId}",method=RequestMethod.POST)
    public Map<String,Object> addCollectionBatch(@PathVariable(value = "templateId") Long templateId){
        try {
            collectionBatchService.addCollectionBatchWithRtInfo(templateId);
            return CommonUtils.getSucResultMap("新增采集记录成功");
        } catch (Exception e) {
            logger.error("新增采集记录异常："+e);
            return CommonUtils.getWarnResultMap(e.getLocalizedMessage());
        }
    }


    /**
     * 分页查询错误列表
     * @author Pan Jianneng
     * @date 2018/11/23 2:56 PM
     * @param batchId
     * @param pageInfo
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @ApiOperation(value = "查询数据校验错误列表",notes = "根据批次ID查询该批次导入有错误的数据列表")
    @RequestMapping(value = "/fail-list/{batchId}",method = RequestMethod.GET)
    public Map<String,Object> pageFindCollectionDataSourceErrorsByBatchId(@PathVariable(value = "batchId") Long batchId,
                                                                          @ModelAttribute PageInfo pageInfo){
        Map<String,Object> resultMap = CommonUtils.getSucResultMap();
        resultMap.put("batchObj",collectionBatchService.findById(batchId));
        resultMap.put("datas",dataCollectionService.pageGetSourceErrorsByBatchId(batchId,pageInfo));
        return resultMap;
    }

    /**
     * 修改错误数据
     * @author Pan Jianneng
     * @date 2018/11/23 3:15 PM
     * @param collectionDataSourceId
     * @param entity
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @ApiOperation(value = "错误数据修改",notes = "根据错误对象id进行修改")
    @RequestMapping(value = "/fail-list/{collectionDataSourceId}",method = RequestMethod.PUT)
    public Map<String,Object> modifyCollectionDataSourceErrorDataByEntity(@PathVariable(value = "collectionDataSourceId") Long collectionDataSourceId,
                                                                          @RequestBody CollectionDataSource entity){
        if(ObjectUtils.isNullObject(entity)){
            return CommonUtils.getErrorResultMap("服务器未能获取修改的数据");
        }
        entity.setId(collectionDataSourceId);
        return dataCollectionService.modifyCollectionDataSourceErrorByEntity(entity);
    }
}
