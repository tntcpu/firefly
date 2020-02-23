package com.tntcpu.agile.chap19;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static com.tntcpu.agile.chap19.TestPaySingleHourlyEmployeeTwoTimeCards.validateHourlyPaycheck;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-23
 */
public class TestPaySingleHourlyEmployeeOvertimeOneTimeCard {
    private PayrollDatabase database;

    @Before
    public void before() {
        database = new InMemoryPayrollDatabase();
    }

    @Test
    public void testPaySingleHourlyEmployeeOvertimeOneTimeCard() throws Exception {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "home", 15.25, database);
        t.execute();
        Calendar cal = Calendar.getInstance();
        cal.set(2020, 1, 21);
        Date payDate = cal.getTime();
        TimeCardTransaction tc = new TimeCardTransaction(payDate, 9.0, empId, database);
        tc.execute();
        PaydayTransaction pt = new PaydayTransaction(payDate, database);
        pt.execute();
        validateHourlyPaycheck(pt, empId, payDate, (8 + 1.5) * 15.25);
    }

}