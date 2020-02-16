package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-16
 */
public class DirectMethod implements PaymentMethod {
    private final String bank;
    private final String aaccount;

    public DirectMethod(String bank, String aaccount) {
        this.bank = bank;
        this.aaccount = aaccount;
    }

    @Override
    public void pay(Paycheck paycheck) {
        paycheck.setField("Disposition", "Direct");
    }

    public String getBank() {
        return bank;
    }

    public String getAaccount() {
        return aaccount;
    }

    public String toString() {
        return String.format("direct deposit into %s:%s", bank, aaccount);
    }
}