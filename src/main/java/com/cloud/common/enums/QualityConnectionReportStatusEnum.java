package com.cloud.common.enums;

/**
 * 数据校验报告
 * @Author daituo
 * @Date 2019-1-18
 **/
public enum QualityConnectionReportStatusEnum {

    SUCCESS(1,"成功"),FAIL(2,"失败");

    private int code;
    private String message;

    QualityConnectionReportStatusEnum(int code, String message) {
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
