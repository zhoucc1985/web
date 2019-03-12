package com.cloud.common.enums;

/**
 * 数据质量检验报告类型
 * @Author daituo
 * @Date 2019-1-17
 **/
public enum QualityReportTypeEnum {

    COLLECTION("C","数据收集校验报告"),DATABASE("D","数据库数据校验报告"),FTP("F","FTP数据校验报告");

    private String code;
    private String message;

    QualityReportTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static QualityReportTypeEnum getByCode(String code) {
        for (QualityReportTypeEnum qualityReportTypeEnum : QualityReportTypeEnum.values()) {
            if (qualityReportTypeEnum.code.equalsIgnoreCase(code)) {
                return qualityReportTypeEnum;
            }
        }
        return null;
    }
}
