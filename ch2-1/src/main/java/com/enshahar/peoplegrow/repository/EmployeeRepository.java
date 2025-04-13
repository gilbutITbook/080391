package com.enshahar.peoplegrow.repository;

import java.util.Optional;
import com.enshahar.peoplegrow.entity.Employee;

public interface EmployeeRepository {
    Optional<Employee> findByEmail(String email);
    void save(Employee employee);
    void update(Employee employee);
}