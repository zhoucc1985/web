package com.cloud.controller.datacollect;

import com.cloud.common.dict.datacollect.SystemDict;
import com.cloud.common.utils.CommonUtils;
import com.cloud.service.datacollect.CollectEnumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举管理
 */
@Api(tags = "数据采集反馈0", description = "枚举管理-CollectEnumCotroller")
@RestController
@RequestMapping(value = "/datacollect/collectenum")
public class CollectEnumCotroller {
    @Autowired
    private CollectEnumService collectEnumService;
    private static final Logger logger = LoggerFactory.getLogger(CollectEnumCotroller.class);
    //查询枚举分类

    /**
     * 查询枚举类 (系统 类别)
     * @return  [firstEnums:ListMap secondEnums:ListMap]
     */
    @ApiOperation(value = "查询枚举类", notes = "查询枚举类信息，系统名称 和 类别名称")
    @RequestMapping(value = "/findEnumData", method = RequestMethod.POST)
    public Map<String, Object> findEnumData() {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            return CommonUtils.getSucResultMap(collectEnumService.findEnumData(SystemDict.systemType));
        } catch (Exception e) {
            logger.error("查询显示枚举 系统+类别出错了" + e);
           return  CommonUtils.getErrorResultMap("查询显示枚举 系统+类别失败了" + e.getMessage());
        }
    }
}
