package com.tntcpu.agile.chap19;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-13
 */
public class TestDeleteEmployee {
    private PayrollDatabase database;

    @Before
    public void before() {
        database = new InMemoryPayrollDatabase();
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        int empId = 4;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Bill",
                "Home", 2500, 3.2, database);
        t.execute();

        Employee e = database.getEmployee(empId);
        assertNotNull(e);

        DeleteEmployeeTransaction dt = new DeleteEmployeeTransaction(empId,database);
        dt.execute();

        e = database.getEmployee(empId);
        assertNull(e);

    }
}