package ch2.v1;

import java.util.List;
class ImportEmployeesService {
    
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
        currentEmployee.setName(currentEmployee.getName());
        currentEmployee.setStartingDate(currentEmployee.getStartingDate());
        currentEmployee.setRole(currentEmployee.getRole());
        
        employees.update(currentEmployee);
        result.updatedEmployee(currentEmployee);
      }
    }
    return result;
  }
}