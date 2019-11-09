package com.tntcpu.algs4.chap1_basis.utils;

/**
 * @program: firefly
 * @desc: 累加器
 * @author: tntcpu
 * @create: 2019-11-09
 */
public class Accumulator {
    private double total;
    private int N;

    public void addDataValue(double val) {
        N++;
        total += val;
    }

    public double mean() {
        return total / N;
    }

    public String toString() {
        return "Mean (" + N + " values): "
                + String.format("%7.5f", mean());
    }
}