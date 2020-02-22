package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-22
 */
public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction {
    public ChangeAffiliationTransaction(int empId, PayrollDatabase database) {
        super(empId, database);
    }

    @Override
    protected void change(Employee e) {
        recordMembership(e);
        e.setAffiliation(getAffiliation());
    }

    protected abstract void recordMembership(Employee e);

    protected abstract Affiliation getAffiliation();
}