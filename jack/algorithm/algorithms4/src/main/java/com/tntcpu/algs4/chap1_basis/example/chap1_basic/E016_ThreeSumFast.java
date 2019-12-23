package com.tntcpu.algs4.chap1_basis.example.chap1_basic;

import com.tntcpu.algs4.chap1_basis.utils.StdOut;
import com.tntcpu.algs4.chap1_basis.utils.StdRandom;
import com.tntcpu.algs4.chap1_basis.utils.StopWatch;

import java.util.Arrays;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2019-12-08
 */
public class E016_ThreeSumFast {
    public static int count(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (E002_BinarySearch.rank(-a[i] - a[j], a) > j) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int n = 30000;
        int max = 1000000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(-max, max);
        }
        StopWatch timer = new StopWatch();
        StdOut.println(count(a));
        double time = timer.elapsedTime();
        System.out.println("time: " + time + "s");
    }
}