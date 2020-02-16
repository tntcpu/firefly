package com.tntcpu.agile.chap19;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-16
 */
public class TestChangeHourlyTransaction {
    private PayrollDatabase database;

    @Before
    public void before() {
        database = new InMemoryPayrollDatabase();
    }

    @Test
    public void testChangeHourlyTransaction() throws Exception {
        int empId = 4;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "sqlary", "home", 1200, database);
        t.execute();
        ChangeHourlyTransaction cht = new ChangeHourlyTransaction(empId, 230, database);
        cht.execute();

        Employee e = database.getEmployee(empId);
        assertNotNull(e);
        PaymentClassification pc = e.getClassification();
        assertNotNull(pc);
        assertTrue(pc instanceof HourlyClassification);
        HourlyClassification hc = (HourlyClassification) pc;
        assertEquals(230, hc.getHourlyRate(), .001);
        PaymentSchedule schedule = e.getSchedule();
        assertTrue(schedule instanceof WeeklySchedule);

    }
}