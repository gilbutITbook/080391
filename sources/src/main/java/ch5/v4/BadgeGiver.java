package ch5.v4;

import java.util.Arrays;
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

    // 초기화 예시로 여기 있으면 안되고 설정 클래스나 메인 함수 등에 있어야 할 것임
    public static List<BadgeRule> createRules() {
        var qualityHero = new BadgeForTrainings(
                Arrays.asList("TESTING", "CODE QUALITY"),
                Badge.QUALITY_HERO);                                   // 4
        var securityCop = new BadgeForTrainings(
                Arrays.asList("SECURITY 101", "SECURITY FOR MOBILE DEVS"),
                Badge.SECURITY_COP);                                  // 5
        var fiveTrainings = new BadgeForQuantity(5, Badge.FIVE_TRAININGS); // 3
        return List.of(
                qualityHero,
                securityCop,
                fiveTrainings
        );
    }
}

interface BadgeRule {
    boolean give(Employee employee);
    Badge badgeToGive();
}
class BadgeForTrainings implements BadgeRule {
    private final List<String> trainings;
    private final Badge badgeToGive;
    public BadgeForTrainings(List<String> trainings,
                             Badge badgeToGive) {                     // 1
        this.trainings = trainings;
        this.badgeToGive = badgeToGive;
    }
    public boolean give(Employee employee) {
        TrainingsTaken trainingsTaken = employee.getTrainingsTaken();
        return trainings.stream()
                .allMatch(training -> trainingsTaken.has(training)); // 2
    }
    public Badge badgeToGive() {
        return badgeToGive;                                    // 3
    }
}
class BadgeForQuantity implements BadgeRule {
    private final int quantity;
    private final Badge badgeToGive;
    public BadgeForQuantity(int quantity,
                            Badge badgeToGive) {                              // 1
        this.quantity = quantity;
        this.badgeToGive = badgeToGive;
    }
    public boolean give(Employee employee) {
        TrainingsTaken trainingsTaken = employee.getTrainingsTaken();
        return trainingsTaken.totalTrainings() >= quantity; // 2
    }
    public Badge badgeToGive() {
        return badgeToGive;
    }
}
