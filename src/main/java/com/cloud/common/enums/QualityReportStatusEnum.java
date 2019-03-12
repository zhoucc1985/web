package com.cloud.common.enums;

/**
 * 数据校验报告
 * @Author daituo
 * @Date 2019-1-18
 **/
public enum  QualityReportStatusEnum {

    SUCCESS(1,"已生成"),WAITING(2,"生成中"),FAIL(3,"生成中"),INITIALIZATION(4,"生成中");
//    FAIL(3,"生成失败"),INITIALIZATION(4,"初始化")

    private int code;
    private String message;

    QualityReportStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
