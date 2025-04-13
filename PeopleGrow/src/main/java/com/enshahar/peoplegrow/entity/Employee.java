package com.enshahar.peoplegrow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Employee {

    @Id
    private String email;
    private String name;
    private LocalDate startingDate;
    private String role;

    public Employee(String name, String email, LocalDate startingData, String role) {
        this.name = name;
        this.email = email;
        this.startingDate = startingData;
        this.role = role;
    }

    public Employee() { // JPA에서 인자 없는 기본 생성자를 요구함
    }

    // 게터와 세터들
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getStartingDate() {
        return startingDate;
    }
    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
