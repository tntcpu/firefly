package com.tntcpu.javabase.tij.enumerated.menu;

import com.tntcpu.javabase.tij.util.Enums;

import java.security.PublicKey;

/**
 * @program: firefly
 * @desc:
 * @author: ZhangZhentao
 * @create: 2020-03-14
 */
public enum Course {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);
    private Food[] values;

    Course(Class<? extends Food> kind) {
        this.values = kind.getEnumConstants();
    }

    public Food randomSelection() {
        return Enums.random(values);
    }
}
