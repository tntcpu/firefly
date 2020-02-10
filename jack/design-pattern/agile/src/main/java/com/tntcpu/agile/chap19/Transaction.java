package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: ZhangZhentao
 * @create: 2020-01-31
 */
public abstract class Transaction {

    protected final PayrollDatabase database;

    public Transaction(PayrollDatabase database) {
        this.database = database;
    }

    public abstract void execute() throws Exception;
}
