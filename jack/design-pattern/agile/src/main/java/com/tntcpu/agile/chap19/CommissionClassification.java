package com.tntcpu.agile.chap19;

import java.util.Date;
import java.util.Hashtable;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-10
 */
public class CommissionClassification extends PaymentClassification {
    private final double baseRate;
    private final double commissionRate;
    private Hashtable<Date, SalesReceipt> salesReceipts = new Hashtable<>();

    public CommissionClassification(double baseRate, double commissionRate) {
        this.baseRate = baseRate;
        this.commissionRate = commissionRate;
    }

    public double getBaseRate() {
        return baseRate;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void addSalesReceipt(SalesReceipt receipt) {
        salesReceipts.put(receipt.getDate(), receipt);
    }

    public SalesReceipt getSaleReceipt(Date time) {
        return salesReceipts.get(time);
    }

    @Override
    public double calculatePay(Paycheck paycheck) {
        double salesTotal = 0;
        for (SalesReceipt receipt : salesReceipts.values()) {
            if (DateUtil.isInPayPeriod(receipt.getDate(), paycheck.getPayPeriodStartDate(),
                    paycheck.getPayPeriodEndDate())) {
                salesTotal += receipt.getSaleAmount();
            }
        }
        return baseRate + (salesTotal * commissionRate * 0.01);
    }

    public String toString() {
        return String.format("%f + %f sales commission", baseRate, commissionRate);
    }


}