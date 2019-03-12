package com.cloud.collectRule;

/**
 * 字符串验证
 */
public class StringValid {

    /**
     * 字符串长度校验
     * @param columnValue 待验证数据
     * @param length 字符串长度
     * @return 验证不通过则返回异常信息，通过则返回null
     * @author daituo
     */
    public static String valid(String columnValue,int length){
        return columnValue.length() <= length ? null : "数据长度大于指定长度[" + length + "]";
    }
}
