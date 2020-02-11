package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-10
 */
public class AddCommissionedEmployee extends AddEmployeeTransaction {
    private final double commissionRate;
    private final double baseRate;

    public AddCommissionedEmployee(int empid, String name, String address, double baseRate,
                                   double commissionRate, PayrollDatabase database) {
        super(empid, name, address, database);
        this.commissionRate = commissionRate;
        this.baseRate = baseRate;
    }

    @Override
    protected PaymentClassification makeClassification() {
        return new CommissionClassification(baseRate, commissionRate);
    }

    @Override
    protected PaymentSchedule makeSchedule() {
        return new BiWeeklySchedule();
    }
}