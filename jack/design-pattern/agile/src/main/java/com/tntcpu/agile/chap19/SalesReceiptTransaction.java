package com.tntcpu.agile.chap19;

import java.util.Date;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-15
 */
public class SalesReceiptTransaction extends Transaction {
    private final int amount;
    private final Date date;
    private final int empId;

    public SalesReceiptTransaction(Date date, int amount, int empId, PayrollDatabase database) {
        super(database);
        this.amount = amount;
        this.date = date;
        this.empId = empId;
    }

    @Override
    public void execute() throws Exception {
        Employee e = database.getEmployee(empId);
        if (e != null) {
            CommissionClassification cc = (CommissionClassification) e.getClassification();
            if (cc != null) {
                SalesReceipt sr = new SalesReceipt(date, amount);
                cc.addSalesReceipt(sr);
            }else {
                throw new Exception("");
            }
        } else {
            throw new Exception("");
        }

    }
}