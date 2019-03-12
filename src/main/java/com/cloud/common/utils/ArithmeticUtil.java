package com.cloud.common.utils;

import java.util.Objects;

/**
 * 算术工具类
 * @Author daituo
 * @Date 2019-1-17
 **/
public class ArithmeticUtil {

    public static int sub(Integer a,Integer b) {
        a = Objects.isNull(a) ? 0 :a;
        b = Objects.isNull(b) ? 0 :b;
        return a - b;
    }
}
