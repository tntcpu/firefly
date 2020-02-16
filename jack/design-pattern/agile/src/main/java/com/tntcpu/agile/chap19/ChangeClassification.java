package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-16
 */
public abstract class ChangeClassification extends ChangeEmployeeTransaction {
    public ChangeClassification(int empId, PayrollDatabase database) {
        super(empId, database);
    }

    @Override
    protected void change(Employee e) {
        e.setClassification(getClassification());
        e.setSchedule(getSchedule());
    }

    protected abstract PaymentSchedule getSchedule();

    protected abstract PaymentClassification getClassification();

}