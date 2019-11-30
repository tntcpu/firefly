package com.tntcpu.javabase.tij.concurrency.test;

import org.apache.commons.lang3.StringUtils;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2019-11-29
 */
public class TestA {
    public static void main(String[] args) {
        String a = null;
        if (StringUtils.isNotBlank(a)){
            System.out.println(a);
        }else {
            System.out.println("a is null");
        }
    }
}