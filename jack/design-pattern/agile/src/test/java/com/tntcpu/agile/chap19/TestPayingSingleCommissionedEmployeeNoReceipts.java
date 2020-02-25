package com.tntcpu.agile.chap19;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static com.tntcpu.agile.chap19.TestPayingSingleHourlyEmployeeNoTimeCards.validatePaycheck;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-26
 */
public class TestPayingSingleCommissionedEmployeeNoReceipts {
    private PayrollDatabase database;

    @Before
    public void before() {
        database = new InMemoryPayrollDatabase();
    }

    @Test
    public void testPayingSingleCommissionedEmployeeNoReceipts() {
        int empId = 2;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Bill", "Home", 1500, 10, database);
        t.execute();
        Calendar cal = Calendar.getInstance();
        cal.set(2020, 1, 14);
        Date payDate = cal.getTime();
        PaydayTransaction pt = new PaydayTransaction(payDate, database);
        pt.execute();
        validatePaycheck(pt, empId, payDate, 1500.00);
    }
}