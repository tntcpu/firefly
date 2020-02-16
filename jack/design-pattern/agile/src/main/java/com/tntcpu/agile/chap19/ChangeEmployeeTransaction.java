package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: ZhangZhentao
 * @create: 2020-02-16
 */
public abstract class ChangeEmployeeTransaction extends Transaction {
    private final int empId;

    public ChangeEmployeeTransaction(int empId, PayrollDatabase database) {
        super(database);
        this.empId = empId;
    }

    public void execute() throws Exception {
        Employee e = database.getEmployee(empId);
        if (e != null) {
            change(e);
        } else {
            throw new Exception("no such employee.");
        }
    }

    protected abstract void change(Employee e);
}
