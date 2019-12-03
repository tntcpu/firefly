package com.tntcpu.algs4.chap1_basis.example.chap1;

import com.tntcpu.algs4.chap1_basis.utils.StdIn;
import com.tntcpu.algs4.chap1_basis.utils.StdOut;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2019-11-25
 */
public class E008_FixedCapacityStackOfStrings {
    private String[] a;
    private int N;

    public E008_FixedCapacityStackOfStrings(int cap) {
        a = new String[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(String item) {
        a[N++] = item;
    }

    public String pop() {
        return a[--N];
    }

    public static void main(String[] args) {
        E008_FixedCapacityStackOfStrings s = new E008_FixedCapacityStackOfStrings(100);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-") && !item.equals(";")) {
                s.push(item);
            } else if (!s.isEmpty() && !item.equals(";")) {
                StdOut.print(s.pop() + " ");
            } else if (item.equals(";")) {
                StdOut.println("(" + s.size() + " left on stack)");
                System.exit(0);
            }
        }
//        StdOut.println("(" + s.size() + " left on stack)");
    }
}



















