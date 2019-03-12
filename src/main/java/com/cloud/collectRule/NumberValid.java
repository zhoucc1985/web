package com.cloud.collectRule;

import java.math.BigDecimal;

public class NumberValid {

    /**
     * 定义整数和浮点数格式类型
     */
    public static final String INT = "int";
    public static final String BIGINT = "bigint";
    public static final String SMALLINT = "smallint";
    public static final String FLOAT = "float";
    public static final String DOUBLE = "double";
    public static final String DECIMAL = "decimal";

    /**
     * 数字大小和格式校验
     * @param columnValue 待验证数据
     * @param columnType 数据类型
     * @Param minVal 最小值,有默认值
     * @Param maxVal 最大值,有默认值
     * @Param decimalDigits 小数位数
     * @return 验证不通过则返回异常信息，通过则返回null
     * @author daituo
     */
    public static String valid(String columnValue, String columnType, Long minVal, Long maxVal, Integer decimalDigits,String columnName) {
        String errorMsg = null;
        switch(columnType){
            case INT: //数据库int对应java int类型
                try {
                    Integer.parseInt(columnValue);
                } catch (Exception e) {
                    errorMsg = ErrorMassage.numberMsg;
                }
                break;
            case BIGINT: //数据库bigint对应java long类型
                try {
                    Long.parseLong(columnValue);
                } catch (Exception e) {
                    errorMsg = ErrorMassage.numberMsg;
                }
                break;
            case SMALLINT: //数据库smallint对应java int类型
                try {
                    Integer.parseInt(columnValue);
                } catch (Exception e) {
                    errorMsg = ErrorMassage.numberMsg;
                }
                break;
            case FLOAT:
                try {
                    Float.parseFloat(columnValue);
                    errorMsg = validScale(columnValue, decimalDigits);
                } catch (Exception e) {
                    errorMsg = ErrorMassage.numberMsg;
                }
                break;
            case DOUBLE:
                try {
                    Double.parseDouble(columnValue);
                    //判断精度小于指定精度
                    errorMsg = validScale(columnValue, decimalDigits);
                } catch (Exception e) {
                    errorMsg = ErrorMassage.numberMsg;
                }
                break;
            case DECIMAL:
                try {
                    BigDecimal.valueOf(Long.parseLong(columnValue));
                    errorMsg = validScale(columnValue, decimalDigits);
                } catch (Exception e) {
                    errorMsg = ErrorMassage.numberMsg;
                }
                break;
            default:
               break;
        }
        return errorMsg;
    }


    /**
     * 校验精度
     * @param columnValue 待验证数据
     * @param decimalDigits 精度
     * @return
     */
    private static String validScale(String columnValue, int decimalDigits) {
        String errorMsg = null;
        //判断精度小于指定精度
        String[] result = columnValue.split("\\.");
        if (result.length > 1) {
            errorMsg = result[1].length() <=  decimalDigits ? null : ErrorMassage.numberMsg;
        }
        return errorMsg;
    }


//    public static void main(String[] args) {
//        NumberValid.valid("3.324","double",null,null,2);
//    }
}
