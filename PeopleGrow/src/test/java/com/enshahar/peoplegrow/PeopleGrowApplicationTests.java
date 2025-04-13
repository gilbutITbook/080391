package com.enshahar.peoplegrow;

import com.enshahar.peoplegrow.entity.Employee;
import com.enshahar.peoplegrow.repository.EmployeeRepository;
import com.enshahar.peoplegrow.service.ImportEmployeesService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class PeopleGrowApplicationTests{

    @Test
    void contextLoads() {
    }

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ImportEmployeesService importEmployeesService;

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
}
