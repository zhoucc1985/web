package com.cloud.controller.report;

import com.cloud.common.excption.BusinessException;
import com.cloud.common.qo.ReportQo;
import com.cloud.common.utils.CommonUtils;
import com.cloud.common.utils.IpUtils;
import com.cloud.service.report.ReportService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 报告 Controller
 *
 * @author huangYl
 * @date 2018/11/15
 **/
@Api(tags = "报告", description = "报告接口")
@RestController
@RequestMapping(value = "/api")
public class ReportController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private ReportService reportService;

    /**
     * 查询报告分页列表
     *
     * @param qo 查询对象
     * @return java.util.Map
     * @author huangYl
     * @date 2018/11/15
     */
    @ApiOperation(value = "查询报告列表")
    @RequestMapping(value = "/reports", method = RequestMethod.POST)
    public Map<String, Object> getUserReports(@RequestBody ReportQo qo) {
        try {
            PageInfo pageInfo = reportService.queryPage(qo);
            return CommonUtils.getSucResultMap(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询报告列表出错", e);
            return CommonUtils.getErrorResultMap();
        }
    }

    /**
     * 生成报告，并注册发送消息
     *
     * @param id 报告id
     * @return java.util.Map<java.lang.String , java.lang.Object>
     * @author huangYl
     * @date 2018/11/19 19:01
     */
    @ApiOperation(value = "生成报告")
    @RequestMapping(value = "/report/generate", method = RequestMethod.POST)
    public Map<String, Object> generateReport(@RequestParam("id") Long id) {
        try {
            String result = reportService.generateReport(id);
            return CommonUtils.getWarnResultMap(result);
        } catch (BusinessException e) {
            logger.error("生成报告出错", e);
            return CommonUtils.getWarnResultMap(e.getMessage());
        } catch (Exception e) {
            logger.error("生成报告出错", e);
            return CommonUtils.getErrorResultMap(e.getMessage());
        }
    }

    /**
     * 根据ID查询报告详情
     *
     * @param id 报告id
     * @return java.util.Map<java.lang.String , java.lang.Object>
     * @author huangYl
     * @date 2018/11/19 19:13
     */
    @ApiOperation(value = "根据ID查询报告详情")
    @RequestMapping(value = "/report/{id}/details", method = RequestMethod.GET)
    public Map<String, Object> getReportDetail(@PathVariable("id") Long id, HttpServletRequest request) {
        try {
            String clientIp = IpUtils.getClientIp(request);
            Map<String, Object> result = reportService.getReportDetailById(id, clientIp);
            return CommonUtils.getSucResultMap(result);
        } catch (Exception e) {
            logger.error("根据ID查询报告详情出错", e);
            return CommonUtils.getErrorResultMap(e.getMessage());
        }
    }

    /**
     * 统计报告详情访问次数
     *
     * @param id 报告id
     * @return java.util.Map
     * @author huangYl
     * @date 2018/11/27
     */
    @ApiIgnore
    @ApiOperation(value = "统计报告详情访问次数")
    @RequestMapping(value = "/report/visit/{id}", method = RequestMethod.POST)
    public Map<String, Object> countReportVisits(@PathVariable("id") Long id, HttpServletRequest request) {
        try {
            String clientIp = IpUtils.getClientIp(request);
            reportService.selectCountReportVisits(id, clientIp);
        } catch (Exception e) {
            logger.error("统计报告详情访问次数出错", e);
        }
        return CommonUtils.getSucResultMap();
    }

    /**
     * 将报告详情保存到json文件中（测试接口，勿删）
     *
     * @param id 报告id
     * @return java.util.Map
     * @author huangYl
     * @date 2018/11/28 17:03
     */
    @ApiIgnore
    @ApiOperation(value = "将报告详情保存到json文件中")
    @RequestMapping(value = "/report/{id}/toJson", method = RequestMethod.POST)
    public Map<String, Object> getReportToJson(@PathVariable("id") Long id, HttpServletRequest request) {
        try {
            String clientIp = IpUtils.getClientIp(request);
            String filePath = reportService.getReportToJson(id, clientIp);
            Map<String, Object> resultMap = CommonUtils.getSucResultMap();
            resultMap.put("filePath",filePath);
            return resultMap;
        } catch (Exception e) {
            logger.error("根据ID查询报告详情出错", e);
            return CommonUtils.getErrorResultMap(e.getMessage());
        }
    }

}
