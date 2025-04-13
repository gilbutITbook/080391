package com.enshahar.peoplegrow.service;

import com.enshahar.peoplegrow.entity.Employee;
import com.enshahar.peoplegrow.lib.csv.CsvParserLibrary;
import com.enshahar.peoplegrow.lib.csv.EmployeeParsedData;
import com.enshahar.peoplegrow.repository.EmployeeRepository;
import com.enshahar.peoplegrow.service.entity.ImportResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportEmployeesService {

    private EmployeeRepository employees;

    public ImportEmployeesService(EmployeeRepository employees) {
        this.employees = employees;
    }

    public ImportResult importFromCsvString(String csv) {
        var result = new ImportResult();

        var csvParser = new CsvParserLibrary();
        csvParser.setMode(CsvParserLibrary.Mode.IGNORE_ERRORS);
        csvParser.setObjectType(EmployeeParsedData.class);
        List<EmployeeParsedData> importedList =
                (List<EmployeeParsedData>)csvParser.parse(csv); // 1

        for(var employee: importedList) {
            var maybeAnEmployee =
                    employees.findByEmail(employee.getEmail()); // 2
            if(maybeAnEmployee.isEmpty()) { // 3
                var newEmployee = new Employee(
                        employee.getName(),
                        employee.getEmail(),
                        employee.getStartingDate(),
                        employee.getRole()
                );

                employees.save(newEmployee);
                result.addedNewEmployee(newEmployee);

            } else { // 4
                var currentEmployee = maybeAnEmployee.get();
                currentEmployee.setName(employee.getName());
                currentEmployee.setStartingDate(employee.getStartingDate());
                currentEmployee.setRole(employee.getRole());

                employees.update(currentEmployee);
                result.updatedEmployee(currentEmployee);
            }
        }
        return result;
    }
}
