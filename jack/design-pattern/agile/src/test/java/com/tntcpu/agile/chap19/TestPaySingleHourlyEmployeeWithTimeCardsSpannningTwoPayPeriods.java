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
 * @create: 2020-02-24
 */
public class TestPaySingleHourlyEmployeeWithTimeCardsSpannningTwoPayPeriods {
    private PayrollDatabase database;

    @Before
    public void before() {
        database = new InMemoryPayrollDatabase();
    }

    @Test
    public void testPaySingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriods() throws Exception {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25, database);
        t.execute();
        Calendar cal = Calendar.getInstance();
        cal.set(2020, 1, 21);
        Date payDate = cal.getTime();
        cal.set(2020, 1, 14);
        Date dateInPreviousPayPeriod = cal.getTime();

        TimeCardTransaction tc = new TimeCardTransaction(payDate, 2.0, empId, database);
        tc.execute();
        TimeCardTransaction tc2 = new TimeCardTransaction(dateInPreviousPayPeriod, 5.0, empId, database);
        tc2.execute();
        PaydayTransaction pt = new PaydayTransaction(payDate, database);
        pt.execute();
        validateHourlyPaycheck(pt, empId, payDate, 2 * 15.25);
    }
}