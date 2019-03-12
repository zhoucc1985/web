package com.cloud.common.vo.datacollection;

import com.cloud.common.dict.CommonDict;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 批次默认查询对象
 * @author xiaoqiang created on 2018/11/22.
 */
@Data
public class CollectionBatchSearchVo {

	private String reportName;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date startTime;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	private int pageNum = 1;

	private int pageSize = CommonDict.DEFAULT_PAGE_SIZE;

}
