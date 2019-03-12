package com.cloud.common.qo;

import com.cloud.common.dict.CommonDict;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 基础查询对象
 *
 * @author huangYl
 * @date 2018/11/15
 **/
@Data
public class BaseQo {
    /**
     * 页码
     */
    private int pageNum = 1;

    /**
     * 页数
     */
    private int pageSize = CommonDict.DEFAULT_PAGE_SIZE;


}
