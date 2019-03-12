package com.cloud.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页参数
 * @author shrChang.Liu
 * @date 2018/4/24 0024 上午 9:28
 * @description xxxx
 */
@ApiModel
public class PageParam {

    @ApiModelProperty(value = "当前页")
    private int pageNum=1;

    @ApiModelProperty(value = "每页大小")
    private int pageSize=10;

    @ApiModelProperty(hidden = true)
    private Integer start;

    public Integer getStart() {
        return start = (this.pageNum - 1) * this.pageSize;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public PageParam(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageParam() {
    }

}
