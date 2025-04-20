package ch2.v3;

import java.util.List;
import java.util.Random;

public class Offering {
    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public boolean isDateUpdated() {
        // 개설 날짜가 변경되었는지 확인하는 로직
        return new Random().nextBoolean();
    }

    public boolean isDescriptionUpdated() {
        // 개설 설명이 변경되었는지 확인하는 로직
        return new Random().nextBoolean();
    }
}
