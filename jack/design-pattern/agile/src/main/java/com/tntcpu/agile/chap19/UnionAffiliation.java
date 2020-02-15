package com.tntcpu.agile.chap19;

import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-16
 */
public class UnionAffiliation implements Affiliation {
    private Hashtable<Date, ServiceCharge> charges = new Hashtable<>();
    private int memberId;
    private final double dues;

    public UnionAffiliation(int memberId, double dues) {
        this.memberId = memberId;
        this.dues = dues;
    }

    public UnionAffiliation() {
        this(-1, 0.0);
    }

    public ServiceCharge getServiceCharge(Date time) {
        return charges.get(time);
    }

    public void addServiceCharge(ServiceCharge sc) {
        charges.put(sc.getDate(), sc);
    }

    public int getMemberId() {
        return memberId;
    }

    public double getDues() {
        return dues;
    }

    @Override
    public double calculateDeductions(Paycheck paycheck) {
        double totalDues = 0;
        int fridays = NumberOfFridaysInPayPeriod(paycheck.getPayPeriodStartDate(), paycheck.getPayPeriodEndDate());
        totalDues = dues * fridays;

        for (ServiceCharge charge : charges.values()) {
            if (DateUtil.isInPayPeriod(charge.getDate(), paycheck.getPayPeriodStartDate(), paycheck.getPayPeriodEndDate())) {
                totalDues += charge.getAmount();
            }
        }
        return totalDues;
    }

    private int NumberOfFridaysInPayPeriod(Date payPeriodStartDate, Date payPeriodEndDate) {
        Calendar dayCa = Calendar.getInstance();
        dayCa.setTime(payPeriodStartDate);
        Calendar payPeriodEndCa = Calendar.getInstance();
        dayCa.setTime(payPeriodEndDate);

        int fridays = 0;
        while (dayCa.before(payPeriodEndCa)) {
            if (dayCa.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                fridays++;
            }
            dayCa.add(Calendar.DATE, 1);
        }
        return fridays;
    }

}