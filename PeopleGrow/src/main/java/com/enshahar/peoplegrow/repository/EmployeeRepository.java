package com.enshahar.peoplegrow.repository;

import com.enshahar.peoplegrow.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);

    default void update(Employee employee) {
        // JPA에서는 엔티티 매니저가 알아서 처리함
    }
}