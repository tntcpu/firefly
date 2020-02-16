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
public class TestChangeDirectTransaction {
    private PayrollDatabase database;

    @Before
    public void before() {
        database = new InMemoryPayrollDatabase();
    }

    @Test
    public void testChangeMethodTransaction() throws Exception {
        int empId = 3;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "method", "home", 2500.0, database);
        t.execute();

        ChangeDirectTransaction cmt = new ChangeDirectTransaction(empId, "ICBC", "62215879", database);
        cmt.execute();

        Employee e = database.getEmployee(empId);
        assertNotNull(e);
        PaymentMethod pm = e.getMethod();
        assertNotNull(pm);
        DirectMethod dm = (DirectMethod) pm;
        assertEquals("ICBC", dm.getBank());
        assertEquals("62215879", dm.getAaccount());
    }
}