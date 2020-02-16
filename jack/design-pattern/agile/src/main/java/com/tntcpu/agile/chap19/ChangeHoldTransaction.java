package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-17
 */
public class ChangeHoldTransaction extends ChangeMethodTransaction {
    public ChangeHoldTransaction(int empId, PayrollDatabase database) {
        super(empId, database);
    }

    @Override
    protected PaymentMethod getMethod() {
        return new HoldMethod();
    }
}