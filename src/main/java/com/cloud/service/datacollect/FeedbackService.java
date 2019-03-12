package com.cloud.service.datacollect;

import com.cloud.entity.datacollect.sysFeedback;

public interface FeedbackService {
	sysFeedback selectfeedbackOpinion(Integer userId);
	void InsertFeedbackOpinion(sysFeedback sysfeedback);
	void updateSysFeedback(sysFeedback sysfeedback);
}
