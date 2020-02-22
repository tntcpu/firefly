package com.tntcpu.agile.chap19;

import java.util.Calendar;
import java.util.Date;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-02
 */
public class MonthlySchedule implements PaymentSchedule {
    private boolean isLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isPayDate(Date payDate) {
        return isLastDayOfMonth(payDate);
    }

    @Override
    public Date getPayPeriodStartDate(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int month = ca.get(Calendar.MONTH);
        while (ca.get(Calendar.MONTH) == month) {
            ca.add(Calendar.DATE, -1);
        }
        return ca.getTime();
    }

    public String toString() {
        return "monthly";
    }

}