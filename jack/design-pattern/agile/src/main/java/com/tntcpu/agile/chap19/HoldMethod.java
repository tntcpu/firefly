package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-02
 */
public class HoldMethod implements PaymentMethod {
    @Override
    public void pay(Paycheck paycheck) {
        paycheck.setField("Disposition", "Hold");
    }

    public String toString() {
        return "hold";
    }
}