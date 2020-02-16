package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-16
 */
public class ChangeCommisionTransaction extends ChangeEmployeeTransaction {
    private final double baseSalary;
    private final double commisionRate;

    public ChangeCommisionTransaction(int empId, double baseSalary, double commisionRate, PayrollDatabase database) {
        super(empId, database);
        this.baseSalary = baseSalary;
        this.commisionRate = commisionRate;
    }

    @Override
    protected void change(Employee e) {
        e.setClassification(getClassification());
        e.setSchedule(getSchedule());
    }

    private PaymentSchedule getSchedule() {
        return new BiWeeklySchedule();
    }

    private PaymentClassification getClassification() {
        return new CommissionClassification(baseSalary, commisionRate);
    }
}