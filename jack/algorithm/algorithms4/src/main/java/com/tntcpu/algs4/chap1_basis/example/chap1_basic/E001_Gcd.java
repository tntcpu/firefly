package com.tntcpu.algs4.chap1_basis.example.chap1_basic;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2019-11-03
 */
public class E001_Gcd {
    public static void main(String[] args) {
        int gcd = gcd(0, 5);
        System.out.println(gcd);
    }

    /**
     * @Return int
     * @Desc: 获取最大公约数方法
     * @Author ZhangZhentao
     * @Date 2019-11-03
     * @Param [p, q]
     */
    public static int gcd(int p, int q) {
        if (q == 0) return q;
        int r = p % q;
        return gcd(q, r);
    }
}