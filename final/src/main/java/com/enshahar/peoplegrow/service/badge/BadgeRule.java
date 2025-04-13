package com.enshahar.peoplegrow.service;

public interface BadgeRule {
    void give(Employee employee);
    Badge badgeToGive();
}
