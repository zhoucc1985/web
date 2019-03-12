package com.cloud.controller.datacollect;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.common.utils.CommonUtils;
import com.cloud.entity.datacollect.sysFeedback;
import com.cloud.service.datacollect.FeedbackService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="数据采集反馈意見")
@RestController
@RequestMapping(value = "/datacollect/feedback")
public class FeedbackController {
	private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);
	@Autowired
	private FeedbackService feedbackService;
	@ApiOperation(value = "查询用戶反饋意見", notes = "查詢用戶反饋意見")
	@RequestMapping(value = "/feedbackOpinion", method = RequestMethod.GET)
	public Map<String, Object> feedbackOpinion(@RequestParam("userId") Integer userId) {
		try {
			return CommonUtils.getSucResultMap(feedbackService.selectfeedbackOpinion(userId));
		} catch (Exception e) {
			logger.error("查询出错:" + e);	
			return CommonUtils.getErrorResultMap("查询出错" + e.getMessage());
		}
	}
	@ApiOperation(value = "查询用戶反饋意見新增", notes = "查詢用戶反饋意見新增")
	@RequestMapping(value = "/addOrUpdate", method = RequestMethod.GET)
	public Map<String, Object> addOrUpdate(sysFeedback sysfeedback,@RequestParam("userId") Integer userId) {
		try {
			if(feedbackService.selectfeedbackOpinion(userId)==null){
				feedbackService.InsertFeedbackOpinion(sysfeedback);
			}else{
				feedbackService.updateSysFeedback(sysfeedback);
			}
			return CommonUtils.getSucResultMap();
		} catch (Exception e) {
			logger.error("查询出错:" + e);	
			return CommonUtils.getErrorResultMap("查询出错" + e.getMessage());
		}
	}

}
