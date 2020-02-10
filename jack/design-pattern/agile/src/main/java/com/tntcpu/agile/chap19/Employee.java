package com.tntcpu.agile.chap19;

import com.sun.management.VMOption;

import java.util.Date;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-01
 */
public class Employee {
    private final int empid;
    private String name;
    private String address;
    private PaymentClassification classification;
    private PaymentSchedule schedule;
    private PaymentMethod method;
    private Affiliation affiliation = new NoAffiliation();

    public Employee(int empid, String name, String address) {
        this.empid = empid;
        this.name = name;
        this.address = address;
    }

    public void payday(Paycheck paycheck) {
        double grossPay = classification.calculatePay(paycheck);
        double deductions = affiliation.calculateDeductions(paycheck);
        double netPay = grossPay - deductions;
        paycheck.setGrossPay(grossPay);
        paycheck.setDeductions(deductions);
        paycheck.setNetPay(netPay);
        method.pay(paycheck);
    }

    public boolean isPayDate(Date date) {
        return schedule.isPayDate(date);
    }

    public Date getPayPeriodStartDate(Date date) {
        return schedule.getPayPeriodStartDate(date);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PaymentClassification getClassification() {
        return classification;
    }

    public void setClassification(PaymentClassification classification) {
        this.classification = classification;
    }

    public PaymentSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(PaymentSchedule schedule) {
        this.schedule = schedule;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public Affiliation getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }

    public Integer getEmpid() {
        return empid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Emp#: ").append(empid).append("   ");
        builder.append(name).append("   ");
        builder.append(address).append("    ");
        builder.append("Paid ").append(classification).append(" ");
        builder.append(schedule);
        builder.append(" by ").append(method);
        return builder.toString();
    }
}