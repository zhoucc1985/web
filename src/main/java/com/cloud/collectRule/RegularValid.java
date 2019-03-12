package com.cloud.collectRule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则验证
 */
public class RegularValid {
    /**
     * 正则验证验证
     * @param cloumnValue
     * @param regularStr
     */
    public static  String valid(String cloumnValue,String regularStr ){
        Pattern numPattern = Pattern.compile(regularStr);
        Matcher matcher = numPattern.matcher(cloumnValue);
        boolean isRight=matcher.matches();
        if(isRight){
            return null;
        }else{
            return ErrorMassage.regularMsg;
        }
    }
}
