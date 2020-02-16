package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-16
 */
public class ChangeAddressTransaction extends ChangeEmployeeTransaction {
    private final String address;

    public ChangeAddressTransaction(int empId, String address, PayrollDatabase database) {
        super(empId, database);
        this.address = address;
    }

    @Override
    protected void change(Employee e) {
        e.setAddress(address);
    }
}