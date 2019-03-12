package com.cloud.common.qo;

import lombok.Data;

/**
 * 基础查询对象
 *
 * @author zhuJT
 * @date 2019/1/11
 **/
@Data
public class BasePageQo {
    /**
     * 页码
     */
    private int pageNum = 1;

    /**
     * 页数
     */
    private int pageSize = 15;


}
