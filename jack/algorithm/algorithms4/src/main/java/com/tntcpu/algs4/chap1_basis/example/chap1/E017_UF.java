package com.tntcpu.algs4.chap1_basis.example.chap1;

import com.tntcpu.algs4.chap1_basis.utils.In;
import com.tntcpu.algs4.chap1_basis.utils.StdIn;
import com.tntcpu.algs4.chap1_basis.utils.StdOut;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2019-12-17
 */
public class E017_UF {
    private int[] id;   //分量id
    private int count;  //分量数量

    public E017_UF(int count) {
        this.count = count;
        id = new int[count];
        for (int i = 0; i < count; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }


    private int find(int p) {
        return id[p];
    }

    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);

        if (pid == qid) {
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
        count--;
    }

    public static void main(String[] args) {
        int[] ints = In.readInts(args[0]);
        int n = ints[0];
        E017_UF uf = new E017_UF(n);
        for (int i = 1; i < ints.length - 1; i+=2) {
            int p = ints[i];
            int q = ints[i + 1];
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}