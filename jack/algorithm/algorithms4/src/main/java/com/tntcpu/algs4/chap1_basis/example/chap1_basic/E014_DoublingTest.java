package com.tntcpu.algs4.chap1_basis.example.chap1_basic;

import com.tntcpu.algs4.chap1_basis.utils.StdOut;
import com.tntcpu.algs4.chap1_basis.utils.StdRandom;
import com.tntcpu.algs4.chap1_basis.utils.StopWatch;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2019-12-07
 */
public class E014_DoublingTest {
    public static double timeTrial(int n) {
        int max = 1000000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(-max, max);
        }
        StopWatch timer = new StopWatch();
        int cnt = E013_ThreeSum.count(a);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        for (int n = 250; n <= 16000; n += n) {
            double time = timeTrial(n);
            StdOut.printf("%7d %5.1f\n", n, time);
        }
    }
}