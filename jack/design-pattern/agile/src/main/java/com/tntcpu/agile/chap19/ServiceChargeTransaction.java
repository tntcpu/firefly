package com.tntcpu.agile.chap19;

import java.util.Date;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-16
 */
public class ServiceChargeTransaction extends Transaction {
    private final int memberId;
    private final Date time;
    private final double charge;

    public ServiceChargeTransaction(int id, Date time, double charge, PayrollDatabase database) {
        super(database);
        this.memberId = id;
        this.time = time;
        this.charge = charge;
    }

    @Override
    public void execute() throws Exception {
        Employee e = database.getUnionMember(memberId);
        if (e != null) {
            UnionAffiliation ua = null;
            if (e.getAffiliation() instanceof UnionAffiliation) {
                ua = (UnionAffiliation) e.getAffiliation();
                if (ua != null) {
                    ua.addServiceCharge(new ServiceCharge(time, charge));
                } else {
                    throw new Exception("tries to add service charge to union member without a union affiliation");
                }
            }
        } else {
            throw new Exception("no such union member.");
        }
    }
}