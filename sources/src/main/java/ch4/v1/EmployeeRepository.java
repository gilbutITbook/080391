package ch4.v1;

import java.util.Optional;

public interface EmployeeRepository {
    Optional<Employee> findByEmail(String email);

    void save(Employee employee);
    void update(Employee employee);
}
