package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-02
 */
public class NoAffiliation implements Affiliation {


    @Override
    public double calculateDeductions(Paycheck paycheck) {
        return 0;
    }
}