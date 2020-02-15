package com.tntcpu.agile.chap19;

import java.util.Date;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-14
 */
public class TimeCardTransaction extends Transaction {
    private final Date date;
    private final double hours;
    private final int empId;


    public TimeCardTransaction(Date date, double hours, int empId, PayrollDatabase database) {
        super(database);
        this.date = date;
        this.hours = hours;
        this.empId = empId;
    }

    @Override
    public void execute() throws Exception {
        Employee e = database.getEmployee(empId);
        if (e != null) {
            HourlyClassification hc = (HourlyClassification) e.getClassification();
            if (hc != null) {
                hc.addTimeCard(new TimeCard(date, hours));
            } else {
                throw new Exception("Tried to add timecard to non-hourly employee");
            }
        } else {
            throw new Exception("no such employee.");
        }
    }
}