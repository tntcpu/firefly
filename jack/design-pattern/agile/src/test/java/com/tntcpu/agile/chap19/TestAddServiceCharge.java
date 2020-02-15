package com.tntcpu.agile.chap19;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-16
 */
public class TestAddServiceCharge {
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

        Employee e = database.getEmployee(empId);
        assertNotNull(e);

        UnionAffiliation af = new UnionAffiliation();
        e.setAffiliation(af);

        int memberId = 86;
        database.addUnionMember(memberId, e);
        Calendar cal = Calendar.getInstance();
        cal.set(2020, 2, 16);
        ServiceChargeTransaction sct = new ServiceChargeTransaction(memberId, cal.getTime(), 12.95, database);
        sct.execute();
        ServiceCharge sc = af.getServiceCharge(cal.getTime());
        assertNotNull(sc);
        assertEquals(12.95, sc.getAmount(), .001);
    }
}