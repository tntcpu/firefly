package com.tntcpu.algs4.chap1_basis.example.chap1;

import com.tntcpu.algs4.chap1_basis.utils.StdOut;
import com.tntcpu.algs4.chap1_basis.utils.StdRandom;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2019-12-05
 */
public class E014_StopWatch {
    private final long start;

    public E014_StopWatch() {
        start = System.currentTimeMillis();
    }

    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(-1000000, 1000000);
        }
        E014_StopWatch timer = new E014_StopWatch();
        int cnt = E013_ThreddSum.count(a);
        double time = timer.elapsedTime();
        StdOut.println(cnt + " triples " + time + " seconds");
    }
}