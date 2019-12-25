package com.tntcpu.algs4.chap1_basis.example.chap2_sort;

import com.tntcpu.algs4.chap1_basis.utils.In;
import com.tntcpu.algs4.chap1_basis.utils.StdOut;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2019-12-24
 */
public class Selection {
    public static void main(String[] args) {

        String[] a = {"bed","bug","yes","zoo","bad","all","yet"};
        sort(a);
        assert isSorted(a);
        show(a);
    }

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (Comparable i : a) {
            StdOut.print(i + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }
}