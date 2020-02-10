package com.tntcpu.agile.chap19;

import java.util.Date;
import java.util.Hashtable;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-02
 */
public class Paycheck {
    private Date payDate;
    private final Date payPeriodStartDate;
    private double grossPay;
    private Hashtable<String, String> fields = new Hashtable<>();
    private double deductions;
    private double netPay;

    public Paycheck(Date payDate, Date payPeriodStartDate) {
        this.payDate = payDate;
        this.payPeriodStartDate = payPeriodStartDate;
    }

    public Date getPayPeriodEndDate() {
        return payDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public void setField(String fieldName, String value) {
        fields.put(fieldName, value);
    }

    public String getField(String filedName) {
        return fields.get(filedName);
    }


    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    public Date getPayPeriodStartDate() {
        return payPeriodStartDate;
    }

}