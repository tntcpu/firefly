package com.tntcpu.algs4.chap1_basis.example.chap1_basic;

import com.tntcpu.algs4.chap1_basis.utils.In;
import com.tntcpu.algs4.chap1_basis.utils.StdIn;
import com.tntcpu.algs4.chap1_basis.utils.StdOut;

import java.util.Arrays;

/**
 * @program: firefly
 * @desc: 二分查找
 * @author: tntcpu
 * @create: 2019-11-04
 */
public class E002_BinarySearch {
    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] whitelist = In.readInts(args[0]);
        Arrays.sort(whitelist);
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (rank(key, whitelist) < 0) {
                StdOut.println(key);
            }
        }
    }
}