package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-16
 */
public class ChangeMailTransaction extends ChangeMethodTransaction{
    private final String address;
    public ChangeMailTransaction(int empId, String address, PayrollDatabase database) {
        super(empId, database);
        this.address = address;
    }

    @Override
    protected PaymentMethod getMethod() {
        return new MailMethod(address);
    }
}