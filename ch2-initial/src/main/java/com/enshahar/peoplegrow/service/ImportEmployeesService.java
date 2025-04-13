package com.enshahar.peoplegrow.service;

import com.enshahar.peoplegrow.entity.Employee;
import com.enshahar.peoplegrow.library.csv.CsvParserLibrary;
import com.enshahar.peoplegrow.repository.EmployeeRepository;
import java.util.List;

public class ImportEmployeesService {

    private EmployeeRepository employees;

    public ImportEmployeesService(EmployeeRepository employees) {
        this.employees = employees;
    }

    public ImportResult importFromCsv(String csv) {
        var result = new ImportResult();

        var csvParser = new CsvParserLibrary<EmployeeParsedData>();
        csvParser.setMode(CsvParserLibrary.Mode.IGNORE_ERRORS);
        csvParser.setObjectType(EmployeeParsedData.class);
        List<EmployeeParsedData> importedList =
                csvParser.parse(csv); // 1

        for(var employee: importedList) {
            var maybeAnEmployee =
                    employees.findByEmail(employee.email()); // 2
            if(maybeAnEmployee.isEmpty()) { // 3
                var newEmployee = new Employee(
                        employee.name(),
                        employee.email(),
                        employee.startingDate(),
                        employee.role()
                );

                employees.save(newEmployee);
                result.addedNewEmployee(newEmployee);

            } else { // 4
                var currentEmployee = maybeAnEmployee.get();
                currentEmployee.setName(employee.name());
                currentEmployee.setStartingDate(employee.startingDate());
                currentEmployee.setRole(employee.role());

                employees.update(currentEmployee);
                result.updatedEmployee(currentEmployee);
            }
        }
        return result;
    }
}

