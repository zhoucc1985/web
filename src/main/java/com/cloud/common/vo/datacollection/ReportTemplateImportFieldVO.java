package com.cloud.common.vo.datacollection;

import lombok.Data;

/**
 * 报表模板导入字段
 *
 * @program cloudIAS
 * @author: Pan Jianneng
 * @create: 2018/11/14 22:32
 */
@Data
public class ReportTemplateImportFieldVO {

    /**
     * 为字段key
     */
    private String key;

    /**
     * 字段名称
     */
    private String value;

}

