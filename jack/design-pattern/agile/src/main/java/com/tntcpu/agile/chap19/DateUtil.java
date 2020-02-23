package com.tntcpu.agile.chap19;

import java.util.Date;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-10
 */
public class DateUtil {
    public static boolean isInPayPeriod(Date theDate, Date startDate, Date endDate) {
        boolean result1 = theDate.after(startDate);
        boolean result2 = (theDate.before(endDate)) || (theDate.equals(endDate));
        return result1 && result2;
    }
}