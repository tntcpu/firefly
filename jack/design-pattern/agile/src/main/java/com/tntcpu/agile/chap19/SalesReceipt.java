package com.tntcpu.agile.chap19;

import java.util.Date;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-10
 */
public class SalesReceipt {
    private Date date;
    private int saleAmount;

    public SalesReceipt(Date date, int amount) {
        this.date = date;
        this.saleAmount = amount;
    }

    public Date getDate() {
        return date;
    }

    public int getSaleAmount() {
        return saleAmount;
    }
}