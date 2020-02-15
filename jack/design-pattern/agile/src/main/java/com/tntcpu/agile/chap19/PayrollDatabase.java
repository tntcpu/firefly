package com.tntcpu.agile.chap19;

import java.util.ArrayList;
import java.util.List;

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

    void addUnionMember(int memberId, Employee e);

    Employee getUnionMember(int id);

    void removeUnionMember(int memberId);

    ArrayList<Integer> getAllEmployeeIds();

    List<Employee> getAllEmployees();
}
