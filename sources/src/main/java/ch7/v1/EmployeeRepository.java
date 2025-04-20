package ch7.v1;

import java.util.Optional;
import java.util.Set;

public interface EmployeeRepository {
    Employee findById(int id);
    Set<Employee> findByLastName(String lastName);
    Optional<Employee> findByEmail(String email);

    void save(Employee employee);
    void update(Employee employee);
}
