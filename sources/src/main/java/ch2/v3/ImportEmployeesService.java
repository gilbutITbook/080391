package ch2.v3;

import java.util.List;

class EmployeeImportCSVParser {
    EmployeeImportCSVParser() {}

    public List<EmployeeParsedData> parse(String csv) { // 1
      var csvParser = new CsvParserLibrary();
      csvParser.setMode(CsvParserLibrary.Mode.IGNORE_ERRORS);
      csvParser.setObjectType(EmployeeParsedData.class);
      return (List<EmployeeParsedData>)csvParser.parse(csv);
    }
    
}

public class ImportEmployeesService {

    private EmployeeRepository employees;
    private EmployeeImportCSVParser csvParser;

    public ImportEmployeesService(EmployeeRepository employees,
                                  EmployeeImportCSVParser csvParser) {                         // 1
        this.employees = employees;
        this.csvParser = new EmployeeImportCSVParser();
    }

    public ImportResult importFromCsvString(String csv) {
        var result = new ImportResult();

        var importEmployees = csvParser.parse(csv);                // 2

        for(var employee: importEmployees) {
            var maybeAnEmployee =
                    employees.findByEmail(employee.getEmail());    // 3
            if(maybeAnEmployee.isEmpty()) {                        // 4    
                createNewEmployee(employee, result);

            } else { // 4
                updateEmployee(employee, maybeAnEmployee.get(), result);
            }
        }
        return result;
    }

    private void createNewEmployee(
        EmployeeParsedData importedEmployee,
        ImportResult result) {                       // 5
          
        var newEmployee = new Employee(
          importedEmployee.getName(),
          importedEmployee.getEmail(),
          importedEmployee.getStartingDate(),
          importedEmployee.getRole()
        );
        
        employees.save(newEmployee);
        result.addedNewEmployee(newEmployee);
      }
    
      private void updateEmployee(
        EmployeeParsedData importedEmployee,
        Employee currentEmployee,
        ImportResult result) {                          // 6
        currentEmployee.setName(importedEmployee.getName());
        currentEmployee.setStartingDate(importedEmployee.getStartingDate());
        currentEmployee.setRole(importedEmployee.getRole());
        
        employees.update(currentEmployee);
        result.updatedEmployee(currentEmployee);
      }
}
