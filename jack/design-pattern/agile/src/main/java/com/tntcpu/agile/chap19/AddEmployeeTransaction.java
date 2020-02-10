package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-02
 */
public abstract class AddEmployeeTransaction extends Transaction {
    private final int empid;
    private final String name;
    private final String address;

    public AddEmployeeTransaction(int empid, String name, String address,
                                  PayrollDatabase database) {
        super(database);
        this.empid = empid;
        this.name = name;
        this.address = address;
    }

    protected abstract PaymentClassification makeClassification();

    protected abstract PaymentSchedule makeSchedule();

    public void execute() {
        PaymentClassification pc = makeClassification();
        PaymentSchedule ps = makeSchedule();
        PaymentMethod pm = new HoldMethod();
        Employee e = new Employee(empid, name, address);
        e.setClassification(pc);
        e.setSchedule(ps);
        e.setMethod(pm);
        database.addEmployee(e);
    }

    public String toString() {
        return String.format("%s  id:%d  name:%s  address:%s",
                getClass().getName(), empid, name, address);
    }
}