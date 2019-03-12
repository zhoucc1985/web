package com.cloud.controller.datacollect;

import com.cloud.common.utils.CommonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * 零时表管理
 */
@Api(tags = "数据采集反馈5", description = "零时表管理-TempTableController")
@RestController
@RequestMapping(value = "/datacollect/template")
public class TempTableController {
    private static final Logger logger = LoggerFactory.getLogger(TempTableController.class);
    /**
     * 创建临时表
     * @param id    模板Id
     * @return
     */
    @ApiOperation(value = "添加临时表", notes = "创建临时表")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> viewDataById(@RequestParam("Id") String id) {

        Map<String, Object> resultMap = new HashMap<>();
        try {
        } catch (Exception e) {
            logger.error("预览模板出错:" + e);
            resultMap = CommonUtils.getWarnResultMap("预览模板失败：" + e.getMessage());
        }

        return resultMap;
    }
    // 删除临时表
}
