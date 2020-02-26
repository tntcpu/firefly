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
 * @create: 2020-02-26
 */
public class TestSalariedUnionMemberDues {
    private PayrollDatabase database;

    @Before
    public void before() {
        database = new InMemoryPayrollDatabase();
    }

    @Test
    public void testSalariedUnionMemberDues() throws Exception {
        int empId = 1;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00, database);
        t.execute();

        int memberId = 7734;
        ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, memberId, 9.42, database);
        cmt.execute();
        Calendar cal = Calendar.getInstance();
        cal.set(2001, 10, 30);
        Date payDate = cal.getTime();
        PaydayTransaction pt = new PaydayTransaction(payDate, database);
        pt.execute();

        validatePaycheck(pt, empId, payDate, 1000.0);
    }

    public static void validatePaycheck(PaydayTransaction pt, int empId, Date payDate, double pay) {
        Paycheck pc = pt.getPaycheck(empId);
        assertNotNull(pc);
        assertEquals(payDate, pc.getPayDate());
        assertEquals(pay, pc.getGrossPay(), .001);
        assertEquals("Hold", pc.getField("Disposition"));
        assertEquals(47.1, pc.getDeductions(), .001);
        assertEquals(pay - 47.1, pc.getNetPay(), .001);
    }
}