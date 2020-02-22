package com.tntcpu.agile.chap19;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-23
 */
@Slf4j
public class TestPaySingleSalariedEmployee {
    private PayrollDatabase database;

    @Before
    public void before() {
        database = new InMemoryPayrollDatabase();
    }

    @Test
    public void testPaySingleSalariedEmployee() throws Exception {
        int empId = 1;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "home", 1000.00, database);
        t.execute();

        Calendar cal = Calendar.getInstance();
        cal.set(2020, 1, 29);
        PaydayTransaction pt = new PaydayTransaction(cal.getTime(), database);
        pt.execute();
        Paycheck pc = pt.getPaycheck(empId);
        log.info(pc.toString());
        assertNotNull(pc);
        assertEquals(cal.getTime(), pc.getPayDate());
        assertEquals(1000.0, pc.getGrossPay(), .001);
        assertEquals("Hold", pc.getField("Disposition"));
        assertEquals(0.0, pc.getDeductions(), .001);
        assertEquals(1000.0, pc.getNetPay(), .001);
    }
}