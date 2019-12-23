package com.tntcpu.algs4.chap1_basis.example.chap1_basic;

import com.tntcpu.algs4.chap1_basis.utils.Counter;
import com.tntcpu.algs4.chap1_basis.utils.StdOut;
import com.tntcpu.algs4.chap1_basis.utils.StdRandom;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2019-11-09
 */
public class Flips {
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        Counter heads = new Counter("heads");
        Counter tails = new Counter("tails");
        for (int i = 0; i < T; i++) {
            if (StdRandom.bernoulli(0.5)) {
                heads.increment();
            } else {
                tails.increment();
            }
        }
        StdOut.println(heads);
        StdOut.println(tails);
        int i = heads.tally() - tails.tally();
        StdOut.println("delta: " + Math.abs(i));
    }
}