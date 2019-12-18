package com.tntcpu.algs4.chap1_basis.example.chap1;

import com.tntcpu.algs4.chap1_basis.utils.In;
import com.tntcpu.algs4.chap1_basis.utils.StdIn;
import com.tntcpu.algs4.chap1_basis.utils.StdOut;
import com.tntcpu.algs4.chap1_basis.utils.StopWatch;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2019-12-17
 */
public class E017_UF {
    private int[] id;   //分量id

    private int[] sz;
    private int count;  //分量数量

    public E017_UF(int count) {
        this.count = count;
        id = new int[count];
        for (int i = 0; i < count; i++) {
            id[i] = i;
        }
        sz = new int[count];
        for (int i = 0; i < count; i++) {
            sz[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }


//    private int find(int p) {
//        return id[p];
//    }
//
//    public void union(int p, int q) {
//        int pid = find(p);
//        int qid = find(q);
//
//        if (pid == qid) {
//            return;
//        }
//        for (int i = 0; i < id.length; i++) {
//            if (id[i] == pid) {
//                id[i] = qid;
//            }
//        }
//        count--;
//    }

    private int find(int p){
        while (p != id[p]){
            p = id[p];
        }
        return p;
    }

//    public void union(int p, int q){
//        int pRoot = find(p);
//        int qRoot = find(q);
//        if (pRoot == qRoot){
//            return;
//        }
//        id[pRoot] = qRoot;
//        count--;
//    }

    public void union(int p, int q)
    {
        int i = find(p);
        int j = find(q);
        if (i == j) return;
// 将小树的根节点连接到大树的根节点
        if (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
        else { id[j] = i; sz[i] += sz[j]; }
        count--;
    }

    public static void main(String[] args) {
        int[] ints = In.readInts(args[0]);
        int n = ints[0];
        E017_UF uf = new E017_UF(n);
        StopWatch timer = new StopWatch();
        for (int i = 1; i < ints.length - 1; i+=2) {
            int p = ints[i];
            int q = ints[i + 1];
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
        double time = timer.elapsedTime();
        StdOut.println(time + " seconds");
    }
}