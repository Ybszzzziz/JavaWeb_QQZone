package com.atguigu.myssm.util;

/**
 * @author Yan
 * @create 2023-04-07 14:51
 **/
public class StringUtils {

    public static boolean isEmpty(String s){
        return s == null || " ".equals(s);
    }

    public static boolean isNotEmpty(String s){
        return !isEmpty(s);
    }

}
