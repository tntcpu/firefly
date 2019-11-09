package com.tntcpu.algs4.chap1_basis.utils;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2019-11-09
 */
public class VisualAccumulator extends Accumulator {
    private double total;
    private int N;

    public VisualAccumulator(int trials, double max) {
        StdDraw.setXscale(0, trials);
        StdDraw.setYscale(0, max);
        StdDraw.setPenRadius(.005);
    }

    public void addDataValue(double val) {
        N++;
        total += val;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(N, val);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(N, total / N);
    }

    public static void main(String[] args) {
        int t = 3000;
        VisualAccumulator a = new VisualAccumulator(t, 1.0);
        for (int i = 0; i < t; i++) {
            a.addDataValue(StdRandom.uniform());
        }
        StdOut.println(a);
    }

}