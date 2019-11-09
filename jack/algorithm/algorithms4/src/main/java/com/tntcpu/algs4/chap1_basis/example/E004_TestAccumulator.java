package com.tntcpu.algs4.chap1_basis.example;

import com.tntcpu.algs4.chap1_basis.utils.Accumulator;
import com.tntcpu.algs4.chap1_basis.utils.StdOut;
import com.tntcpu.algs4.chap1_basis.utils.StdRandom;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2019-11-09
 */
public class E004_TestAccumulator {
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        Accumulator a = new Accumulator();
        for (int i = 0; i < T; i++) {
            a.addDataValue(StdRandom.uniform());
        }
        System.out.println(a);
    }
}