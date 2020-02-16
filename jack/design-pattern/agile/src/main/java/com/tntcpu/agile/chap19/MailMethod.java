package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-16
 */
public class MailMethod implements PaymentMethod {
    private final String address;

    public MailMethod(String address) {
        this.address = address;
    }

    @Override
    public void pay(Paycheck paycheck) {
        paycheck.setField("disposition", "mail");
    }

    public String getAddress() {
        return address;
    }

    public String toString() {
        return "please mail :" + address;
    }
}