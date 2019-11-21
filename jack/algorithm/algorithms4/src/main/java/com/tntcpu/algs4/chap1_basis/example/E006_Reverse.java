package com.tntcpu.algs4.chap1_basis.example;

import com.tntcpu.algs4.chap1_basis.utils.StdIn;
import com.tntcpu.algs4.chap1_basis.utils.StdOut;

import java.util.Stack;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2019-11-22
 */
public class E006_Reverse {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        for (int i = 0; i < 5; i++) {
            StdOut.println(stack.pop());
        }


    }
}