package com.enshahar.peoplegrow.lib.csv;

import java.time.LocalDate;

// record를 쓸 경우 특히 기본 생성자로 객체를 생성할 수 없는등 제약이 많다.
public class EmployeeParsedData {
    String name;
    String email;
    LocalDate startingDate;
    String role;

    public EmployeeParsedData() {
    }

    // 게터들
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public LocalDate getStartingDate() {
        return startingDate;
    }
    public String getRole() {
        return role;
    }
}