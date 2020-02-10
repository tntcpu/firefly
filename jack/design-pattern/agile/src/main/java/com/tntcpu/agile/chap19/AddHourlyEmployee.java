package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-10
 */
public class AddHourlyEmployee extends AddEmployeeTransaction {
    private final double hourlyRate;

    public AddHourlyEmployee(int empid, String name, String address, double hourlyRate, PayrollDatabase database) {
        super(empid, name, address, database);
        this.hourlyRate = hourlyRate;

    }

    @Override
    protected PaymentClassification makeClassification() {
        return new HourlyClassification(hourlyRate);
    }

    @Override
    protected PaymentSchedule makeSchedule() {
        return new WeeklySchedule();
    }
}