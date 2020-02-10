package com.tntcpu.agile.chap19;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-01-31
 */
public class TestAddSalariedEmployee {
    private PayrollDatabase database;

    @Before
    public void before() {
        database = new InMemoryPayrollDatabase();
    }

    @Test
    public void testAddSalariedEmployee() {
        int empId = 1;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00, database);
        t.execute();
        Employee e = database.getEmployee(empId);

        PaymentClassification pc = e.getClassification();
        assertTrue(pc instanceof SalariedClassification);
        SalariedClassification sc = (SalariedClassification) pc;

        assertEquals(1000.00, sc.getSalary(), .001);
        PaymentSchedule ps = e.getSchedule();
        assertTrue(ps instanceof MonthlySchedule);

        PaymentMethod pm = e.getMethod();
        assertTrue(pm instanceof HoldMethod);
    }

}