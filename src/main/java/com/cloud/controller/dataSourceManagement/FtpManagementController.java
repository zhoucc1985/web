package com.cloud.controller.dataSourceManagement;

import com.cloud.common.qo.FtpManagementQo;
import com.cloud.common.utils.CommonUtils;
import com.cloud.entity.datamanagement.Ftpdatasource;
import com.cloud.service.datamanagement.FtpManagementService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * ftp数据源收集
 *
 * @author zhujt
 * @date 2019/1/11
 **/
@Api(tags = "ftp数据源收集", description = "ftp数据源收集")
@RestController
@RequestMapping(value = "/ftpManagement")
public class FtpManagementController {

    private static final Logger logger = LoggerFactory.getLogger(FtpManagementController.class);

    @Autowired
    private FtpManagementService ftpManagementService;


    /**
     * 查询报告分页列表
     *
     * @param qo 查询对象
     * @return java.util.Map
     * @author zhuJT
     * @date 2019/1/11
     */
    @ApiOperation(value = "查询FTP列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Map<String, Object> getLists(@RequestBody FtpManagementQo qo) {
        try {
            PageInfo pageInfo = ftpManagementService.queryPage(qo);
            return CommonUtils.getSucResultMap(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询FTP列表出错", e);
            return CommonUtils.getErrorResultMap();
        }
    }

    /**
     * 新增或编辑ftp地址
     *
     * @param ftpdatasource ftp数据源对象
     * @return map
     * @author zhuJT
     * @date 2019/01/14
     */
    @ApiOperation(value = "编辑Ftp数据源")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Map<String, Object> editDataSource(@RequestBody Ftpdatasource ftpdatasource) {
        try {
            ftpManagementService.editDataSource(ftpdatasource);
            return CommonUtils.getSucResultMap();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑Ftp数据源出错", e);
            return CommonUtils.getErrorResultMap("编辑Ftp数据源出错:"+e.getMessage());
        }
    }

    @ApiOperation(value = "测试连接")
    @RequestMapping(value = "/connectTest", method = RequestMethod.POST)
    public Map<String, Object> connectTest(@RequestBody Ftpdatasource ftpdatasource) {
        try {
            boolean isLogin = ftpManagementService.connectTest(ftpdatasource);
            if (!isLogin) {
                throw new Exception("连接失败，请重新连接");
            }
            return CommonUtils.getSucResultMap("连接成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("连接错误", e.getMessage());
            return CommonUtils.getErrorResultMap("连接失败" + e.getMessage());
        }
    }

    @ApiOperation(value = "删除数据源")
    @RequestMapping(value = "/delete/{ftpId}", method = RequestMethod.POST)
    public Map<String, Object> connectTest(@PathVariable(value = "ftpId") Long ftpId) {
        try {
            ftpManagementService.delete(ftpId);
            return CommonUtils.getSucResultMap("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除数据源失败", e.getMessage());
            return CommonUtils.getErrorResultMap("删除数据源失败：" + e.getMessage());
        }
    }


}
