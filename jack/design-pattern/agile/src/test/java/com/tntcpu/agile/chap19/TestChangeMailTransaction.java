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
public class TestChangeMailTransaction {
    private PayrollDatabase database;

    @Before
    public void before() {
        database = new InMemoryPayrollDatabase();
    }

    @Test
    public void testChangeMailTransaction() throws Exception {
        int empId = 5;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "mail", "home", 25000.0, database);
        t.execute();

        ChangeMailTransaction cmt = new ChangeMailTransaction(empId, "compony", database);
        cmt.execute();

        Employee e = database.getEmployee(empId);
        assertNotNull(e);
        PaymentMethod pm = e.getMethod();
        assertNotNull(pm);
        MailMethod mm = (MailMethod) pm;
        assertEquals("compony", mm.getAddress());

    }
}