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
public class TestChangeNameTransaction {
    private PayrollDatabase database;

    @Before
    public void before() {
        database = new InMemoryPayrollDatabase();
    }

    @Test
    public void testAddServiceCharge() throws Exception {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "home", 15.25, database);
        t.execute();

        ChangeNameTransaction cnt = new ChangeNameTransaction(empId, "Bob", database);
        cnt.execute();

        Employee e = database.getEmployee(empId);
        assertNotNull(e);
        assertEquals("Bob", e.getName());
    }
}