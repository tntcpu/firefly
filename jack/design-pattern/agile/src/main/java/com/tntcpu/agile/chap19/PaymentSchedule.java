package com.tntcpu.agile.chap19;

import java.util.Date;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-02
 */
public interface PaymentSchedule {
    boolean isPayDate(Date payDate);

    Date getPayPeriodStartDate(Date date);
}