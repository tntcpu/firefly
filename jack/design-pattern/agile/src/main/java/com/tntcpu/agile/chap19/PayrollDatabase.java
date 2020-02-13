package com.tntcpu.agile.chap19;

/**
 * @program: firefly
 * @desc:
 * @author: ZhangZhentao
 * @create: 2020-02-01
 */
public interface PayrollDatabase {
    void addEmployee(Employee employee);

    Employee getEmployee(int id);

    void deleteEmployee(int id);
}
