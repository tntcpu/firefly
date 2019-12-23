package com.tntcpu.algs4.chap1_basis.example.chap1_basic;


import com.tntcpu.algs4.chap1_basis.utils.Counter;
import com.tntcpu.algs4.chap1_basis.utils.StdOut;
import com.tntcpu.algs4.chap1_basis.utils.StdRandom;

/**
 * @program: firefly
 * @desc: 掷骰子计数正反面次数
 * @author: tntcpu
 * @create: 2019-11-06
 */
public class E003_Rolls {
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        int SIDES = 6;
        Counter[] rolls = new Counter[SIDES + 1];
        for (int i = 1; i <= SIDES; i++) {
            rolls[i] = new Counter(i + "'s");
        }
        for (int t = 0; t < T; t++) {
            int result = StdRandom.uniform(1, SIDES + 1);
            rolls[result].increment();
        }
        for (int i = 0; i <= SIDES; i++) {
            StdOut.println(rolls[i]);
        }
    }
}