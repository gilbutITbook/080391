package ch5.v2;

import java.util.Optional;

public interface EmployeeRepository {
    Optional<Employee> findByEmail(String email);

    void save(Employee employee);
    void update(Employee employee);
}
