package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-16
 */
public class ChangeHourlyTransaction extends ChangeClassification {
    private final double hourlyRate;

    public ChangeHourlyTransaction(int empId, double hourlyRate, PayrollDatabase database) {
        super(empId, database);
        this.hourlyRate = hourlyRate;
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }

    @Override
    protected PaymentClassification getClassification() {
        return new HourlyClassification(hourlyRate);
    }
}