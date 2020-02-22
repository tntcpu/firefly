package com.tntcpu.agile.chap19;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-22
 */
public class TestChangeUnaffiliatedMember {
    private PayrollDatabase database;

    @Before
    public void before() {
        database = new InMemoryPayrollDatabase();
    }

    @Test
    public void testChangeUnaffiliatedMember() throws Exception {
        int empId = 10;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "home", 15.25, database);
        t.execute();

        int memberId = 7743;
        new ChangeMemberTransaction(empId, memberId, 99.42, database).execute();
        ChangeUnaffiliatedTransaction cut = new ChangeUnaffiliatedTransaction(empId, database);
        cut.execute();
        Employee e = database.getEmployee(empId);
        assertNotNull(e);
        Affiliation affiliation = e.getAffiliation();
        assertNotNull(affiliation);
        assertTrue(affiliation instanceof NoAffiliation);
        Employee member = database.getUnionMember(memberId);
        assertNull(member);
    }
}