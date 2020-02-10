package com.tntcpu.agile.chap19;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2020-02-01
 */
public class InMemoryPayrollDatabase implements PayrollDatabase {
    private static Hashtable<Integer, Employee> employees = new Hashtable<>();
    private static Hashtable<Integer, Employee> unionMembers = new Hashtable<>();

    @Override
    public void addEmployee(Employee employee) {
        employees.put(employee.getEmpid(), employee);

    }

    @Override
    public Employee getEmployee(int id) {
        return employees.get(id);
    }

    public void deleteEmployee(int id) {
        employees.remove(id);
    }

    public void addUnionMember(int id, Employee e) {
        unionMembers.put(id, e);
    }

    public Employee getUnionMember(int id) {
        return unionMembers.get(id);
    }

    public void removeUnionMember(int memberId) {
        unionMembers.remove(memberId);
    }

    public ArrayList<Integer> getAllEmployeeIds() {
        return new ArrayList<>(employees.keySet());
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public void clear() {
        employees.clear();
        unionMembers.clear();
    }
}