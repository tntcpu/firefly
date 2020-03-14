package com.tntcpu.javabase.tij.util;

import java.util.Random;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-03-14
 */
public class Enums {
    private static Random rand = new Random(47);
    public static <T extends Enum<T>> T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }
    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }
}