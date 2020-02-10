package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-02
 */
public class SalariedClassification extends PaymentClassification {
    private final double salary;

    public SalariedClassification(double salary) {
        this.salary = salary;
    }

    @Override
    public double calculatePay(Paycheck paycheck) {
        return salary;
    }

    public double getSalary() {
        return salary;
    }

    public String toString() {
        return String.format("%f", salary);
    }
}