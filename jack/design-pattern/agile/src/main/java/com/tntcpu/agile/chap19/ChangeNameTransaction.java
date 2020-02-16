package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-16
 */
public class ChangeNameTransaction extends ChangeEmployeeTransaction {
    private final String name;

    public ChangeNameTransaction(int empId, String name, PayrollDatabase database) {
        super(empId, database);
        this.name = name;
    }

    @Override
    protected void change(Employee e) {
        e.setName(name);
    }
}