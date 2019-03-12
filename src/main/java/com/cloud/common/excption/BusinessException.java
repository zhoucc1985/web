package com.cloud.common.excption;

import lombok.Data;

/**
 * 业务异常类
 *
 * @program cloudIAS
 * @author: Pan Jianneng
 * @create: 2018/11/15
 */
@Data
public class BusinessException extends RuntimeException{

    private String resourceId;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message,String resourceId) {
        super(message);
        this.resourceId = resourceId;
    }
}