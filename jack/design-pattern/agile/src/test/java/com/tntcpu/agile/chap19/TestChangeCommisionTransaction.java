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
public class TestChangeCommisionTransaction {
    private PayrollDatabase database;

    @Before
    public void before() {
        database = new InMemoryPayrollDatabase();
    }

    @Test
    public void testChangeCommisionTransaction() throws Exception {
        int empId = 5;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "home", 2500.0, database);
        t.execute();
        ChangeCommisionTransaction cct = new ChangeCommisionTransaction(empId, 1250.0, 5.6, database);
        cct.execute();
        Employee e = database.getEmployee(empId);
        assertNotNull(e);
        PaymentClassification pc = e.getClassification();
        assertNotNull(pc);
        assertTrue(pc instanceof CommissionClassification);
        CommissionClassification cc = (CommissionClassification) pc;
        double baseRate = cc.getBaseRate();
        double commissionRate = cc.getCommissionRate();
        assertEquals(1250.0, baseRate, .001);
        assertEquals(5.6, commissionRate, .001);
        PaymentSchedule ps = e.getSchedule();
        assertTrue(ps instanceof BiWeeklySchedule);

    }
}