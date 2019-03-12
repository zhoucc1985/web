package com.cloud.controller.dataSourceManagement;

import com.cloud.common.qo.DBManagementQo;
import com.cloud.common.utils.CommonUtils;
import com.cloud.entity.datamanagement.DBDatasource;
import com.cloud.service.datamanagement.DBManagementService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.Map;

/**
 * DB数据源收集
 *
 * @author zhujt
 * @date 2019/1/11
 **/
@Api(tags = "DB数据源收集", description = "DB数据源收集")
@RestController
@RequestMapping(value = "/DBManagement")
public class DBManagementController {
    private static final Logger logger = LoggerFactory.getLogger(DBManagementController.class);

    @Autowired
    private DBManagementService dbManagementService;

    /**
     * 查询报告分页列表
     *
     * @param qo 查询对象
     * @return java.util.Map
     * @author zhuJT
     * @date 2019/1/16
     */
    @ApiOperation(value = "查询DB数据源列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Map<String, Object> getLists(@RequestBody DBManagementQo qo) {
        try {
            PageInfo pageInfo = dbManagementService.queryPage(qo);
            return CommonUtils.getSucResultMap(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询DB数据源列表出错：", e);
            return CommonUtils.getErrorResultMap();
        }
    }

    @ApiOperation(value = "连接测试")
    @RequestMapping(value = "/connectTest", method = RequestMethod.POST)
    public Map<String, Object> connectTest(@RequestBody DBDatasource datasource) {
        Map<String, Object> resultMap = CommonUtils.getSucResultMap();
        try {
            Connection connection = dbManagementService.connectTest(datasource);
            if (connection != null) {
                resultMap = CommonUtils.getSucResultMap("连接成功");
            } else {
                resultMap = CommonUtils.getErrorResultMap("连接失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("连接测试失败", e);
            resultMap = CommonUtils.getErrorResultMap("连接测试失败：" + e.getMessage());
        }
        return resultMap;
    }

    @ApiOperation(value = "删除数据源")
    @RequestMapping(value = "/delete/{dataSourceId}", method = RequestMethod.POST)
    public Map<String, Object> connectTest(@PathVariable(value = "dataSourceId") Long dataSourceId) {
        Map<String, Object> resultMap = CommonUtils.getSucResultMap("删除成功");
        try {
            dbManagementService.delete(dataSourceId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除数据源失败", e.getMessage());
            resultMap = CommonUtils.getErrorResultMap("删除数据源失败：" + e.getMessage());
        }
        return resultMap;
    }

    @ApiOperation(value = "编辑DB数据源")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Map<String, Object> editDataSource(@RequestBody DBDatasource datasource) {
        Map<String, Object> resultMap = CommonUtils.getSucResultMap();
        try {
            dbManagementService.editDataSource(datasource);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑DB数据源出错", e);
            resultMap = CommonUtils.getErrorResultMap("编辑DB数据源出错：" + e.getMessage());
        }
        return resultMap;
    }

    @ApiOperation(value = "采集DB数据源到大数据平台")
    @RequestMapping(value = "/collectDataSource", method = RequestMethod.POST)
    public Map<String, Object> collectDataSource(@RequestParam(value = "dataSourceId") Long dataSourceId) {
        try {
            boolean result = dbManagementService.collectDataSource(dataSourceId);
            if (result) {
                return CommonUtils.getSucResultMap("推送成功");
            }else {
                return CommonUtils.getErrorResultMap("推送到大数据平台采集失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("推送到大数据平台采集失败", e);
             return  CommonUtils.getErrorResultMap("推送到大数据平台采集:" + e.getMessage());
        }
    }

}
