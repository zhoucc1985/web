package com.cloud.common.qo;

import lombok.Data;

/**
 * 校验列表查询对象
 */
@Data
public class CheckQo extends BasePageQo {
    /**
     * 表名称
     */
    private String tableName;

    /**
     * 系统名称
     */
    private Integer systemName;

    /**
     * 系统分类
     */
    private Integer firstTypeCode;

    /**
     * 子分类
     */
    private Integer secondTypeCode;
    
    /**
     * 权限组织id
     */
    private String orgId;
}
