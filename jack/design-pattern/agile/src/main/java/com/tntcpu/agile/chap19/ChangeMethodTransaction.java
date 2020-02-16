package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-16
 */
public abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction {

    public ChangeMethodTransaction(int empId, PayrollDatabase database) {
        super(empId, database);
    }

    @Override
    protected void change(Employee e) {
        e.setMethod(getMethod());
    }

    protected abstract PaymentMethod getMethod();

}