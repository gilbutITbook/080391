package com.enshahar.peoplegrow;

import com.enshahar.peoplegrow.entity.Employee;
import com.enshahar.peoplegrow.repository.EmployeeRepository;
import com.enshahar.peoplegrow.service.ImportEmployeesService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class PeopleGrowApplicationTests {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ImportEmployeesService importEmployeesService;

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    void contextLoads() {
    }

    @Test
    void importEmployees() {
        var result = importEmployeesService.importFromCsvString(
                "name,email,startingDate,role\n" +
                "hyunsok oh,test@gmail.com,2023-10-01,developer\n"
        );

        var employees = result.getEmployees();
        assert employees.size() == 1;

        var first = employees.toArray()[0];

        assert first instanceof Employee;
        var employee = (Employee) first;
        assert employee.getName().equals("hyunsok oh");
        assert employee.getEmail().equals("test@gmail.com");
        assert employee.getStartingDate().equals(LocalDate.of(2023,10,01));
        assert employee.getRole().equals("developer");
    }

    @Test
    void importEmployeesThenUpdate() {
        // first, import employee from csv
        var result = importEmployeesService.importFromCsvString(
                "name,email,startingDate,role\n" +
                "hyunsok oh,test@gmail.com,2023-10-01,developer\n"
        );

        var employees = result.getEmployees();
        assert employees.size() == 1;

        var first = employees.toArray()[0];

        assert first instanceof Employee;
        var employee = (Employee) first;
        assert employee.getName().equals("hyunsok oh");
        assert employee.getEmail().equals("test@gmail.com");
        assert employee.getStartingDate().equals(LocalDate.of(2023,10,01));
        assert employee.getRole().equals("developer");


        // second, import new employyee and update existing employee from csv
        var result2 = importEmployeesService.importFromCsvString(
                "name,email,startingDate,role\n" +
                "hyunsok oh,test@gmail.com,2023-10-01,senior developer\n" +
                "joyce lee,test2@gmail.com,2024-10-01,intern developer\n"
        );

        var employees2 = result2.getEmployees();
        assert employees2.size() == 2;

        var hyunsok = employees2.stream().filter(e -> e.getEmail().equals("test@gmail.com")).findFirst().get();
        var joyce = employees2.stream().filter(e -> e.getEmail().equals("test2@gmail.com")).findFirst().get();

        // test hyunsok's fields
        assert hyunsok.getName().equals("hyunsok oh");
        assert hyunsok.getEmail().equals("test@gmail.com");
        assert hyunsok.getStartingDate().equals(LocalDate.of(2023,10,01));
        assert hyunsok.getRole().equals("senior developer");

        // test joyce's fields  
        assert joyce.getName().equals("joyce lee");
        assert joyce.getEmail().equals("test2@gmail.com");
        assert joyce.getStartingDate().equals(LocalDate.of(2024,10,01));
        assert joyce.getRole().equals("intern developer");
    }
}
