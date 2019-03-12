package com.cloud.common.vo.datacollection;

import lombok.Data;

/**
 * 错误字段对象
 *
 * @program cloudIAS
 * @author: Pan Jianneng
 * @create: 2018/11/21 20:50
 */
@Data
public class CollectionDataErrorObjVO {

    /**
     * 字段名称
     */
    private String name;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 错误字段的值
     */
    private String value;
    /**
     * 是否错误
     */
    private Boolean error;


}

