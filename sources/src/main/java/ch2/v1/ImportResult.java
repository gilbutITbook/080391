package ch2.v1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ImportResult {
    private final List<Employee> employees;
    public ImportResult() {
        this.employees = new ArrayList<>();
    }
    public void addedNewEmployee(Employee newEmployee) {
        this.employees.add(newEmployee);
    }

    public void updatedEmployee(Employee updatedEmployee) {
        this.employees.removeIf(employee -> employee.getEmail().equals(updatedEmployee.getEmail()));
        this.employees.add(updatedEmployee);
    }

    public Collection<Employee> getEmployees() {
        return Collections.unmodifiableCollection(employees);
    }
}
