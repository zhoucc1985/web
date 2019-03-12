package com.cloud.common.dict.cloud;

/**
 * 正则匹配
 * @author zhoucc
 */
public class ValidatorRegexpDict {
    private ValidatorRegexpDict(){

    }
    /**
     * mac地址规则
     */
    public static final String MAC = "^\\s*$|(^[A-F0-9]{2}(-[A-F0-9]{2}){5}$)";
    /**
     * IP地址规则
     */
    public static final String IP = "^\\s*$|^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$";
    
    /**
     * 国内手机号码
     */
    public static final String PHONE = "0?(13|14|15|17|18|19)[0-9]{9}";
    
    /**
     * 邮箱地址
     */
    public static final String EMAIL = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";

}
