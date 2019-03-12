package com.cloud.entity.datacollect;

import java.util.Date;

import com.cloud.common.vo.ExcelResolve;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CollectBatchids {
	@ExcelResolve(titleName = "编号")
    @ApiModelProperty(notes = "编号")
	private int id;
	@ExcelResolve(titleName = "批次名称")
    @ApiModelProperty(notes = "批次名称")
	private String name;
	@ExcelResolve(titleName = "日期 yyyy-mm-dd")
    @ApiModelProperty(notes = "日期 yyyy-mm-dd")
	private String date;
	@ExcelResolve(titleName = "批次编号增长值")
    @ApiModelProperty(notes = "批次编号增长值")
	private int increatNum;
	@Override
	public String toString() {
		return "CollectBatchids [id=" + id + ", name=" + name + ", date=" + date + ", increatNum=" + increatNum + "]";
	}
}
