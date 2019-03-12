package com.cloud.service.datacollect.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cloud.entity.datacollect.sysFeedback;
import com.cloud.mapper.master.datacollect.SysFeedbackMapper;
import com.cloud.service.datacollect.FeedbackService;
@Service
public class FeedbackServiceImpl implements FeedbackService{
	@Resource
	private SysFeedbackMapper sysFeedbackMapper;
	/**
	 * 查詢當前用戶是否已經錄入反饋意見
	 */
	@Override
	public sysFeedback selectfeedbackOpinion(Integer userId) {
		if(sysFeedbackMapper.selectByUserId(userId)==null){
			return null;
		}else{
			return sysFeedbackMapper.selectByUserId(userId);
		}
	}
	@Override
	public void InsertFeedbackOpinion(sysFeedback sysfeedback) {
		sysFeedbackMapper.insert(sysfeedback);
	}
	@Override
	public void updateSysFeedback(sysFeedback sysfeedback) {
		sysFeedbackMapper.updateSysFeedback(sysfeedback);
	}

}
