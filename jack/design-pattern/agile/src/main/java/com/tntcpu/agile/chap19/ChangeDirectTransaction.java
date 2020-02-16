package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-16
 */
public class ChangeDirectTransaction extends ChangeMethodTransaction {
    private final String bank;
    private final String account;

    public ChangeDirectTransaction(int empId, String bank, String account, PayrollDatabase database) {
        super(empId, database);
        this.bank = bank;
        this.account = account;
    }

    @Override
    protected PaymentMethod getMethod() {
        return new DirectMethod(bank, account);
    }
}