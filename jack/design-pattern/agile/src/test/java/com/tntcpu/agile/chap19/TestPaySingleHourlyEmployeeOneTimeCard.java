package com.tntcpu.agile.chap19;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-23
 */
public class TestPaySingleHourlyEmployeeOneTimeCard {
    private PayrollDatabase database;

    @Before
    public void before() {
        database = new InMemoryPayrollDatabase();
    }
    @Test
    public void testPaySingleHourlyEmployeeOneTimeCard() throws Exception {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25, database);
        t.execute();
        Calendar cal = Calendar.getInstance();
        cal.set(2020, 1, 20);
        Date payDate = cal.getTime();
        TimeCardTransaction tc = new TimeCardTransaction(payDate, 9.0, empId, database);
        tc.execute();
        PaydayTransaction pt = new PaydayTransaction(payDate, database);
        pt.execute();
        Paycheck pc = pt.getPaycheck(empId);
        assertNull(pc);
    }

}