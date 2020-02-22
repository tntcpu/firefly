package com.tntcpu.agile.chap19;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-23
 */
public class PaydayTransaction extends Transaction {
    private final Date payDate;
    private Hashtable<Integer, Paycheck> paychecks = new Hashtable<>();

    public PaydayTransaction(Date payDate, PayrollDatabase database) {
        super(database);
        this.payDate = payDate;
    }

    @Override
    public void execute() {
        ArrayList<Integer> empIds = database.getAllEmployeeIds();

        for (int empId : empIds) {
            Employee employee = database.getEmployee(empId);
            if (employee.isPayDate(payDate)) {
                Date startDate = employee.getPayPeriodStartDate(payDate);
                Paycheck pc = new Paycheck(startDate, payDate);
                paychecks.put(empId, pc);
                employee.payday(pc);
            }
        }
    }

    public Paycheck getPaycheck(int empId) {
        return paychecks.get(empId);
    }
}