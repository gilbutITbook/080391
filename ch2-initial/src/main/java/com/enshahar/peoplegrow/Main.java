package com.enshahar.peoplegrow;

import com.enshahar.peoplegrow.entity.Employee;
import com.enshahar.peoplegrow.hibernate.HibernateUtil;
import com.enshahar.peoplegrow.repository.EmployeeRepository;
import com.enshahar.peoplegrow.repository.EmployeeRepositoryImpl;
import com.enshahar.peoplegrow.service.ImportEmployeesService;
import com.enshahar.peoplegrow.service.ImportResult;
import org.hibernate.Session;
import java.time.LocalDate;

import java.time.LocalDate;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ImportEmployeesService service = new ImportEmployeesService(new EmployeeRepositoryImpl());

        // Sample CSV data
        String csvData = """
            name,email,startingDate,role
            John Doe,john.doe@example.com,2023-01-15,Developer
            Jane Smith,jane.smith@example.com,2023-02-01,Manager
        """;

        // Import employees
        ImportResult result = service.importFromCsv(csvData);

        // Print results
        System.out.println("New employees added: " + result.getAddedEmployees().size());
        System.out.println("Employees updated: " + result.getUpdatedEmployees().size());
    }
}