package com.enshahar.peoplegrow.service;

import java.time.LocalDate;

public record EmployeeParsedData(
        String name,
        String email,
        LocalDate startingDate,
        String role
) { } // 5
