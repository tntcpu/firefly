package com.tntcpu.agile.chap19;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-17
 */
public class TestChangeHoldTransaction {
    private PayrollDatabase database;

    @Before
    public void before() {
        database = new InMemoryPayrollDatabase();
    }

    @Test
    public void testChangeHoldTransaction() throws Exception {
        int empId = 6;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "mike", "home", 35000.0, database);
        t.execute();
        new ChangeDirectTransaction(empId, "ccb", "1233", database).execute();
        Employee e = database.getEmployee(empId);
        PaymentMethod pm1 = e.getMethod();
        assertTrue(pm1 instanceof DirectMethod);
        ChangeHoldTransaction cht = new ChangeHoldTransaction(empId, database);
        cht.execute();

        assertNotNull(e);
        PaymentMethod pm = e.getMethod();
        assertNotNull(pm);
        assertTrue(pm instanceof HoldMethod);
    }
}