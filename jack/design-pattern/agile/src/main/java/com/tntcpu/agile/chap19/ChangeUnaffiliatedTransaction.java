package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-22
 */
public class ChangeUnaffiliatedTransaction extends ChangeAffiliationTransaction {

    public ChangeUnaffiliatedTransaction(int empId, PayrollDatabase database) {
        super(empId, database);
    }

    @Override
    protected void recordMembership(Employee e) {

    }

    @Override
    protected Affiliation getAffiliation() {
        return new NoAffiliation();
    }
}