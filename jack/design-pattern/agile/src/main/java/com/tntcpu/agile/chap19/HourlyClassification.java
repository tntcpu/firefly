package com.tntcpu.agile.chap19;

import java.util.Date;
import java.util.Hashtable;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-10
 */
public class HourlyClassification extends PaymentClassification {
    private double hourlyRate;
    private Hashtable<Date, TimeCard> timeCards = new Hashtable<>();

    public HourlyClassification(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public TimeCard getTimeCard(Date date) {
        return timeCards.get(date);
    }

    public void addTimeCard(TimeCard card) {
        timeCards.put(card.getDate(), card);
    }

    @Override
    public double calculatePay(Paycheck paycheck) {
        double totalPay = 0.0;
        for (TimeCard timeCard : timeCards.values()) {
            if (DateUtil.isInPayPeriod(timeCard.getDate(), paycheck.getPayPeriodStartDate(), paycheck.getPayPeriodEndDate())) {
                totalPay += calculatePayForTimeCard(timeCard);
            }
        }
        return totalPay;
    }

    private double calculatePayForTimeCard(TimeCard timeCard) {
        double overtimeHours = Math.max(0.0, timeCard.getHours() - 8);
        double normalHours = timeCard.getHours() - overtimeHours;

        return hourlyRate * normalHours + hourlyRate * 1.5 * overtimeHours;
    }

    public String toString() {
        return String.format("%f/hr", hourlyRate);
    }
}