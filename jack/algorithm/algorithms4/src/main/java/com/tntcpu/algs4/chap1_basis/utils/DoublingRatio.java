package com.tntcpu.algs4.chap1_basis.utils;

import static com.tntcpu.algs4.chap1_basis.example.chap1.E014_DoublingTest.timeTrial;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2019-12-08
 */
public class DoublingRatio {
    // public static double timeTrial(int N);
    // same as for DoublingTest (page 177)
    public static void main(String[] args) {
        double prev = timeTrial(125);
        for (int n = 250; true; n += n) {
            double time = timeTrial(n);
            StdOut.printf("%6d %7.1f ", n, time);
            StdOut.printf("%5.1f\n", time / prev);
            prev = time;
        }
    }
}