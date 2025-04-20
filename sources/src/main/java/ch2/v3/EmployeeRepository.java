package ch2.v3;

import java.util.Optional;

public interface EmployeeRepository {
    Optional<Employee> findByEmail(String email);
    void save(Employee employee);
    void update(Employee employee);
}
