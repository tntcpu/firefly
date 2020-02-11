package com.tntcpu.agile.chap19;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-10
 */
public class TestAddCommissionedEmployee {
    private PayrollDatabase database;

    @Before
    public void before() {
        database = new InMemoryPayrollDatabase();
    }

    @Test
    public void testAddCommissionedEmployee() {
        int empid = 3;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empid, "justin", "home", 2500, 9.5, database);
        t.execute();

        Employee e = database.getEmployee(empid);
        assertEquals("justin",e.getName());

        PaymentClassification pc = e.getClassification();
        assertTrue(pc instanceof  CommissionClassification);
        CommissionClassification cc = (CommissionClassification) pc;

        assertEquals(2500,cc.getBaseRate(),.001);
        assertEquals(9.5,cc.getCommissionRate(),.001);
        PaymentSchedule ps = e.getSchedule();
        assertTrue(ps instanceof BiWeeklySchedule);

        PaymentMethod pm = e.getMethod();
        assertTrue(pm instanceof HoldMethod);

    }





















}