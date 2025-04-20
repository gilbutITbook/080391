package ch2.v5;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class Offering {
    private Set<Employee> employees;

    public Set<Employee> getEmployees() {
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

    public boolean isImportantInfoUpdated() {                    // 1
        return this.isDateUpdated() || this.isDescriptionUpdated();
    }

    public Set<Employee> getWaitingList() {
        // 대기자 목록을 가져오는 로직
        return Set.of();
    }
}
