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
 * @create: 2020-02-28
 */
public class TestHourlyUnionMemberServiceCharge {
    private PayrollDatabase database;

    @Before
    public void before() {
        database = new InMemoryPayrollDatabase();
    }

    @Test
    public void testHourlyUnionMemberServiceCharge() throws Exception {
        int empId = 1;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.24, database);
        t.execute();
        int memberId = 7734;
        ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, memberId, 9.42, database);
        cmt.execute();
        Calendar cal = Calendar.getInstance();
        cal.set(2001, 10, 9);
        Date payDate = cal.getTime();
        ServiceChargeTransaction sct = new ServiceChargeTransaction(memberId, payDate, 19.42, database);
        sct.execute();
        TimeCardTransaction tct = new TimeCardTransaction(payDate, 8.0, empId, database);
        tct.execute();
        PaydayTransaction pt = new PaydayTransaction(payDate, database);
        pt.execute();
        Paycheck pc = pt.getPaycheck(empId);

        assertNotNull(pc);
        assertEquals(payDate, pc.getPayPeriodEndDate());
        assertEquals(8 * 15.24, pc.getGrossPay(), .001);
        assertEquals("Hold", pc.getField("Disposition"));
        assertEquals(9.42 + 19.42, pc.getDeductions(), .001);
        assertEquals((8 * 15.24) - (9.42 + 19.42), pc.getNetPay(), .001);

    }
}