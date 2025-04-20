package ch5.v3;

import java.util.List;

public class BadgeGiver {
    private final List<BadgeRule> rules;
    public BadgeGiver(List<BadgeRule> rules) {  // 1
        this.rules = rules;
    }
    public void give(Employee employee) {
        for(BadgeRule rule : rules) {             // 2
            if(rule.give(employee)) {
                employee.winBadge(rule.badgeToGive());
            }
        }
    }
}

interface BadgeRule {
    boolean give(Employee employee);
    Badge badgeToGive();
}

class QualityHero implements BadgeRule { // 1
    public boolean give(Employee employee) {
        TrainingsTaken trainingsTaken = employee.getTrainingsTaken();
        return trainingsTaken.has("TESTING") &&
                trainingsTaken.has("CODE QUALITY");
    }
    public Badge badgeToGive() {
        return Badge.QUALITY_HERO;
    }
}
class SecurityCop implements BadgeRule { // 2
    public boolean give(Employee employee) {
        TrainingsTaken trainingsTaken = employee.getTrainingsTaken();
        return trainingsTaken.has("SECURITY 101") &&
                trainingsTaken.has("SECURITY FOR MOBILE DEVS");
    }
    public Badge badgeToGive() {
        return Badge.SECURITY_COP;
    }
}
class FiveTrainings implements BadgeRule { // 3
    public boolean give(Employee employee) {
        TrainingsTaken trainingsTaken = employee.getTrainingsTaken();
        return trainingsTaken.totalTrainings() >= 5;
    }
    public Badge badgeToGive() {
        return Badge.FIVE_TRAININGS;
    }
}
class TenTrainings implements BadgeRule { // 3
    public boolean give(Employee employee) {
        TrainingsTaken trainingsTaken = employee.getTrainingsTaken();
        return trainingsTaken.totalTrainings() >= 10;
    }
    public Badge badgeToGive() {
        return Badge.FIVE_TRAININGS;
    }
}
class OnFire implements BadgeRule { // 3
    public boolean give(Employee employee) {
        TrainingsTaken trainingsTaken = employee.getTrainingsTaken();
        return trainingsTaken.trainingsInPast3Months() >= 3;
    }
    public Badge badgeToGive() {
        return Badge.ON_FIRE;
    }
}
