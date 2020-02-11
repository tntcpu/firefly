package com.tntcpu.agile.chap19;

import java.util.Calendar;
import java.util.Date;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-10
 */
public class BiWeeklySchedule implements PaymentSchedule {
    @Override
    public boolean isPayDate(Date payDate) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(payDate);
        return ca.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY
                && ca.get(Calendar.DATE) % 2 == 0;
    }

    @Override
    public Date getPayPeriodStartDate(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DATE, -13);
        return ca.getTime();
    }

    public String toStirng() {
        return "bi-weekly";
    }
}