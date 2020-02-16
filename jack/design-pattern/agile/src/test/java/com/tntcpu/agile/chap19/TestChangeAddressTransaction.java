package com.tntcpu.agile.chap19;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-16
 */
public class TestChangeAddressTransaction {
    private PayrollDatabase database;

    @Before
    public void before() {
        database = new InMemoryPayrollDatabase();
    }

    @Test
    public void testChangeAddressTransaction() throws Exception {
        int empId = 3;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Hash", "home", 2000, database);
        t.execute();

        ChangeAddressTransaction cat = new ChangeAddressTransaction(empId, "company", database);
        cat.execute();

        Employee e = database.getEmployee(empId);
        assertNotNull(e);

        assertEquals("company", e.getAddress());
    }
}