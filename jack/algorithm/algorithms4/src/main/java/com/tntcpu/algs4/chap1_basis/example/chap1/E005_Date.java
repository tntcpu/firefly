package com.tntcpu.algs4.chap1_basis.example.chap1;

import java.util.Date;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2019-11-21
 */
public class E005_Date {
    private final int month;
    private final int day;
    private final int year;

    public E005_Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public String toString() {
        return getMonth() + "/" + getDay() + "/" + getYear();
    }

    public boolean equals(Object x) {
        if (this == x) return true;
        if (x == null) return false;
        if (x.getClass() != this.getClass()) return false;
        Date that = (Date) x;
        if (this.getDay() != that.getDay()) return false;
        if (this.getMonth() != that.getMonth()) return false;
        if (this.getYear() != that.getYear()) return false;
        return true;
    }
}