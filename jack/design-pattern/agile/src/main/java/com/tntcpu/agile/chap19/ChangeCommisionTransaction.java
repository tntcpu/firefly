package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-16
 */
public class ChangeCommisionTransaction extends ChangeClassificationTransaction {
    private final double baseSalary;
    private final double commisionRate;

    public ChangeCommisionTransaction(int empId, double baseSalary, double commisionRate, PayrollDatabase database) {
        super(empId, database);
        this.baseSalary = baseSalary;
        this.commisionRate = commisionRate;
    }

    public PaymentSchedule getSchedule() {
        return new BiWeeklySchedule();
    }

    public PaymentClassification getClassification() {
        return new CommissionClassification(baseSalary, commisionRate);
    }
}