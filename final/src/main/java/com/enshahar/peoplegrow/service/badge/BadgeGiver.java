package com.enshahar.peoplegrow.service;

import com.enshahar.peoplegrow.entity.Employee;

public class BadgeGiver {
    public void give(Employee employee) {    // 1
        new BadgesForTrainings().give(employee);
        new BadgesForQuantity().give(employee);
    }
}

class BadgesForTrainings {
    public void give(Employee employee) {
        TrainingsTaken trainingsTaken = employee.getTrainingsTaken();
        // 품질 관련 교육을 받은 경우 배지를 받는다     // 2
        if (trainingsTaken.has("TESTING") &&
                trainingsTaken.has("CODE QUALITY")) {
            assign(employee, Badge.QUALITY_HERO);
        }
        // 보안 관련 교육을 모두 들으면 배지를 받는다
        if (trainingsTaken.has("SECURITY 101") &&
                trainingsTaken.has("SECURITY FOR MOBILE DEVS")) {
            assign(employee, Badge.SECURITY_COP);
        }
        // ... 다른 배치 수여 규칙들

    }
    private void assign(Employee employee, Badge badge) {
        employee.winBadge(badge);
    }
}

class BadgesForQuantity {
    public void give(Employee employee) {  // 3
        TrainingsTaken trainingsTaken = employee.getTrainingsTaken();
        if(trainingsTaken.totalTrainings() >= 5) {
            assign(employee, Badge.FIVE_TRAININGS);
        }
        if(trainingsTaken.totalTrainings() >= 10) {
            assign(employee, Badge.TEN_TRAININGS);
        }
        if(trainingsTaken.trainingsInPast3Months() >= 3) {
            assign(employee, Badge.ON_FIRE);
        }
    }
    private void assign(Employee employee, Badge badge) {
        employee.winBadge(badge);
    }
}
