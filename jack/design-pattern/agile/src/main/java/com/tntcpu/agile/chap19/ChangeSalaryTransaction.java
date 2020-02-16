package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-16
 */
public class ChangeSalaryTransaction extends ChangeEmployeeTransaction {
    private final double salary;

    public ChangeSalaryTransaction(int empId, int salary, PayrollDatabase database) {
        super(empId, database);
        this.salary = salary;
    }

    @Override
    protected void change(Employee e) {
        e.setClassification(getClassification());
        e.setSchedule(getSchedule());
    }

    private PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }

    private PaymentClassification getClassification() {
        return new SalariedClassification(salary);
    }
}