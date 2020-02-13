package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-13
 */
public class DeleteEmployeeTransaction extends Transaction {
    private final int id;

    public DeleteEmployeeTransaction(int id, PayrollDatabase database) {
        super(database);
        this.id = id;
    }

    @Override
    public void execute() {
        database.deleteEmployee(id);
    }
}