package com.tntcpu.agile.chap19;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-14
 */
public class TestTimeCardTransaction {
    private PayrollDatabase database;

    @Before
    public void before() {
        database = new InMemoryPayrollDatabase();
    }

    @Test
    public void testTimeCardTransaction() throws Exception {
        int empId = 5;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25, database);
        t.execute();
        Calendar cal = Calendar.getInstance();
        cal.set(2005, 7, 31);
        TimeCardTransaction tct = new TimeCardTransaction(cal.getTime(), 8.0, empId, database);
        tct.execute();

        Employee e = database.getEmployee(empId);
        assertNotNull(e);

        PaymentClassification pc = e.getClassification();
        assertTrue(pc instanceof HourlyClassification);
        HourlyClassification hc = (HourlyClassification) pc;

        TimeCard tc = hc.getTimeCard(cal.getTime());
        assertNotNull(tc);
        assertEquals(8.0, tc.getHours(), .01);
    }
}