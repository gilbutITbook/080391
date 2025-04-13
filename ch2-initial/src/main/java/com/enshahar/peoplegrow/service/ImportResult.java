package com.enshahar.peoplegrow.service;

import com.enshahar.peoplegrow.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class ImportResult {
    private final List<Employee> addedEmployees = new ArrayList<>();
    private final List<Employee> updatedEmployees = new ArrayList<>();

    public void addedNewEmployee(Employee employee) {
        addedEmployees.add(employee);
    }

    public void updatedEmployee(Employee employee) {
        updatedEmployees.add(employee);
    }

    public List<Employee> getAddedEmployees() {
        return addedEmployees;
    }

    public List<Employee> getUpdatedEmployees() {
        return updatedEmployees;
    }
}
