package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-16
 */
public class ChangeSalaryTransaction extends ChangeClassificationTransaction {
    private final double salary;

    public ChangeSalaryTransaction(int empId, int salary, PayrollDatabase database) {
        super(empId, database);
        this.salary = salary;
    }

    public PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }

    public PaymentClassification getClassification() {
        return new SalariedClassification(salary);
    }
}