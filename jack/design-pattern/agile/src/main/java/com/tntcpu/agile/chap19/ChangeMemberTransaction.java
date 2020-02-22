package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-22
 */
public class ChangeMemberTransaction extends ChangeAffiliationTransaction {
    private final int memberId;
    private final double dues;

    public ChangeMemberTransaction(int empId, int memberId, double dues, PayrollDatabase database) {
        super(empId, database);
        this.memberId = memberId;
        this.dues = dues;
    }

    @Override
    protected void recordMembership(Employee e) {
        database.addUnionMember(memberId, e);
    }

    @Override
    protected Affiliation getAffiliation() {
        return new UnionAffiliation(memberId, dues);
    }
}