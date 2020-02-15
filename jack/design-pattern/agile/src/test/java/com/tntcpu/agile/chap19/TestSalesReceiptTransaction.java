package com.tntcpu.agile.chap19;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-15
 */
public class TestSalesReceiptTransaction {
    private PayrollDatabase database;

    @Before
    public void before() {
        database = new InMemoryPayrollDatabase();
    }

    @Test
    public void testSalesReceiptTransaction() throws Exception {
        int empId = 6;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "six", "home",
                2000, 18.9, database);
        t.execute();

        Calendar cal = Calendar.getInstance();
        cal.set(2020, 2, 15);
        SalesReceiptTransaction s = new SalesReceiptTransaction(cal.getTime(), 200, empId, database);
        s.execute();

        Employee e = database.getEmployee(empId);
        assertNotNull(e);

        PaymentClassification pc = e.getClassification();
        assertTrue(pc instanceof PaymentClassification);
        CommissionClassification cc = (CommissionClassification) pc;

        SalesReceipt saleReceipt = cc.getSaleReceipt(cal.getTime());
        assertNotNull(saleReceipt);
        assertEquals(200,saleReceipt.getSaleAmount(),.001);
    }
}