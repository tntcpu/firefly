package com.tntcpu.algs4.chap1_basis.example;

/**
 * @author: gpscp
 * @date: 2019-11-03
 * @desc:
 */
public class E001_Gcd {
    public static void main(String[] args) {
        int gcd = gcd(0, 5);
        System.out.println(gcd);
    }

    public static int gcd(int p, int q) {
        if (q == 0) return q;
        int r = p % q;
        return gcd(q, r);
    }
}