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
public class TestChangeSalaryTransaction {
    private PayrollDatabase database;

    @Before
    public void before() {
        database = new InMemoryPayrollDatabase();
    }

    @Test
    public void testChangeSalaryTransaction() throws Exception {
        int empId = 4;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "home", 2500, 3.2, database);
        t.execute();

        ChangeSalaryTransaction cst = new ChangeSalaryTransaction(empId, 3200, database);
        cst.execute();

        Employee e = database.getEmployee(empId);
        assertNotNull(e);

        PaymentClassification pc = e.getClassification();
        assertNotNull(pc);
        assertTrue(pc instanceof SalariedClassification);

        SalariedClassification sc = (SalariedClassification) pc;
        assertEquals(sc.getSalary(), 3200, .001);

        PaymentSchedule ps = e.getSchedule();
        assertTrue(ps instanceof MonthlySchedule);
    }
}