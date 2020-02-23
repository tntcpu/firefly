package com.tntcpu.agile.chap19;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-23
 */
public class TestPayingSingleHourlyEmployeeNoTimeCards {
    private PayrollDatabase database;

    @Before
    public void before() {
        database = new InMemoryPayrollDatabase();
    }

    @Test
    public void testPayingSingleHourlyEmployeeNoTimeCards() {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25, database);
        t.execute();
        Calendar cal = Calendar.getInstance();
        cal.set(2020, 1, 21);
        Date payDate = cal.getTime();
        PaydayTransaction pt = new PaydayTransaction(payDate, database);
        pt.execute();
        validatePaycheck(pt, empId, payDate, 0.0);
    }

    private void validatePaycheck(PaydayTransaction pt, int empId, Date payDate, double pay) {
        Paycheck pc = pt.getPaycheck(empId);
        assertNotNull(pc);
        assertEquals(payDate, pc.getPayDate());
        assertEquals(pay, pc.getGrossPay(), .001);
        assertEquals("Hold", pc.getField("Disposition"));
        assertEquals(0.0, pc.getDeductions(), .001);
        assertEquals(pay, pc.getNetPay(), .001);
    }
}