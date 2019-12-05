package com.tntcpu.algs4.chap1_basis.example.chap1;

import com.tntcpu.algs4.chap1_basis.utils.In;
import com.tntcpu.algs4.chap1_basis.utils.StdOut;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2019-12-05
 */
public class E013_ThreddSum {
    public static int count(int[] a){
        int n = a.length;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                for (int k = j+1; k < n; k++) {
                    if (a[i] + a[j] + a[k] == 0){
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        StdOut.println(count(a));
    }
}