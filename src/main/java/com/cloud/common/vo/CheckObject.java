package com.cloud.common.vo;

import lombok.Data;

/**
 * 添加文件描述
 *
 * @author Pan jianneng
 * @time 2018/10/23 10:28
 */
@Data
public class CheckObject {

    private String name;
    private boolean isError;
    private String msg;
    private String chekcRule;
    private String value;
}
