package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-02
 */
public class AddSalariedEmployee extends AddEmployeeTransaction {
    private final double salary;

    public AddSalariedEmployee(int empid, String name, String address, double salary, PayrollDatabase database) {
        super(empid, name, address, database);
        this.salary = salary;
    }

    @Override
    protected PaymentClassification makeClassification() {
        return new SalariedClassification(salary);
    }

    @Override
    protected PaymentSchedule makeSchedule() {
        return new MonthlySchedule();
    }

}