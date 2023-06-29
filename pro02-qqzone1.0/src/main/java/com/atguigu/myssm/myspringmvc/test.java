package com.atguigu.myssm.myspringmvc;

import java.io.InputStream;

/**
 * @author Yan
 * @create 2023-04-09 14:43
 **/
public class test {
    public static void main(String[] args) {
        test test = new test();

    }
    public test(){
        InputStream stream = getClass().getResourceAsStream("/applicationContext.xml");
        if (stream != null){
            System.out.println("1");
        }else {
            System.out.println("2");
        }
    }

}
