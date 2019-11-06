package com.tntcpu.algs4.chap1_basis.utils;

/**
 * @program: firefly
 * @desc: 计数器
 * @author: tntcpu
 * @create: 2019-11-06
 */
public class Counter implements Comparable<Counter> {
    private final String name;
    private int count = 0;

    public Counter(String id) {
        this.name = id;
    }

    public void increment() {
        count++;
    }

    public int tally() {
        return count;
    }

    public String toString() {
        return count + " " + name;
    }

    @Override
    public int compareTo(Counter that) {
        if (this.count < that.count) return -1;
        else if (this.count > that.count) return +1;
        else return 0;
    }
}