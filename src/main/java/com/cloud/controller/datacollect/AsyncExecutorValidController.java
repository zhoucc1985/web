package com.cloud.controller.datacollect;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.common.utils.CommonUtils;
import com.cloud.service.datacollect.CollectBatchidsService;
import com.cloud.service.datacollect.RealTableService;
import com.cloud.service.datacollect.RuleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "数据采集反馈 测试", description = "查询所有真实表列的属性参数-AsyncExecutorValidController")
@RestController
@RequestMapping(value = "/asyncExecutorValidController")
public class AsyncExecutorValidController {
	private static final Logger logger = LoggerFactory.getLogger(DataCollectController.class);
	@Resource
	private RuleService ruleService;
	@Resource
	private RealTableService realTableService;
	@Resource
	private CollectBatchidsService collectBatchidsService;

	@ApiOperation(value = "查询所有真实表列的属性参数", notes = "查询所有真实表列的属性参数")
	@RequestMapping(value = "/asyncExecutorValid", method = RequestMethod.GET)
	public Map<String, Object> asyncExecutorValid() {
		try {
			ruleService.insertCollectColumns();
			return CommonUtils.getSucResultMap();
		} catch (Exception e) {
			logger.error("查询出错:" + e);	
			return CommonUtils.getErrorResultMap("查询出错" + e.getMessage());
		}
	}
}
