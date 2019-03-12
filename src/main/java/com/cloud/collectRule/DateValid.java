package com.cloud.collectRule;


import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 日期格式验证
 */
public class DateValid {

    /**
     * 定义日志格式
     */
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE = "yyyy-MM-dd";
    public static final String DATE_ABBREVIATE = "yyyyMMdd";

    /**
     * 日期格式校验
     * @param columnValue 待验证数据
     * @param datePattern 日志格式
     * @return 验证不通过则返回异常信息，通过则返回null
     * @author daituo
     */
    public static String validDate(String columnValue,String datePattern){
        String errorMsg = null;
        SimpleDateFormat sdf;
        switch(datePattern){
            case DATE_TIME:
                sdf = new SimpleDateFormat(DATE_TIME);
                try {
                    sdf.parse(columnValue);
                } catch (ParseException e) {
                    errorMsg = "数据不符合[" + DATE_TIME + "]格式";
                }
                break;
            case DATE:
                sdf = new SimpleDateFormat(DATE);
                try {
                    sdf.parse(columnValue);
                } catch (ParseException e) {
                    errorMsg = "数据不符合[" + DATE + "]格式";
                }
                break;
            case DATE_ABBREVIATE:
                sdf = new SimpleDateFormat(DATE_ABBREVIATE);
                try {
                    sdf.parse(columnValue);
                } catch (ParseException e) {
                    errorMsg = "数据不符合[" + DATE_ABBREVIATE + "]格式";
                }
                break;
            default:
               break;
        }
        return errorMsg;
    }

    /**
     * 日期格式判断
     * @param columnValue 数据值
     * @param ruleType 规则名称（数据类型 date varchar max_length等）
     * @param formatStr 规则-格式化字符串
     * @return
     */
    public static String valid(String columnValue,String ruleType,String formatStr){
        String errorMsg="";
        if(formatStr != null){
            SimpleDateFormat fmt = new SimpleDateFormat(formatStr);
            try {
                fmt.format(columnValue);
            } catch (Exception e) {
                e.printStackTrace();
                errorMsg="日期格式不对:"+columnValue+"，正确格式为："+formatStr;
            }
        }else{
            errorMsg = null;
        }
        return errorMsg;
    }


}