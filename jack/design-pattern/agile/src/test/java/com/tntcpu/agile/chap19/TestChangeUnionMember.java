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
public class TestChangeUnionMember {
    private PayrollDatabase database;

    @Before
    public void before() {
        database = new InMemoryPayrollDatabase();
    }

    @Test
    public void testChangeUnionMember() throws Exception {
        int empId = 9;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "home", 15.25, database);
        t.execute();

        int memberId = 7743;
        ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, memberId, 99.42, database);
        cmt.execute();

        Employee e = database.getEmployee(empId);
        assertNotNull(e);
        Affiliation affiliation = e.getAffiliation();
        assertNotNull(affiliation);
        assertTrue(affiliation instanceof UnionAffiliation);
        UnionAffiliation ua = (UnionAffiliation) affiliation;
        assertEquals(99.42, ua.getDues(), .001);
        Employee member = database.getUnionMember(memberId);
        assertNotNull(member);
        assertEquals(e, member);

    }
}