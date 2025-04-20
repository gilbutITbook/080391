package ch6.v1;

import java.util.Arrays;
import java.util.List;

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

public class BadgeForTrainingsFactory implements BadgeRuleFactory {
    public List<BadgeRule> createRules() {
        // DB에 접근해 데이터를 얻고,
        // 각각의 데이터에 대해 BadgeForQuantity 클래스를 인스턴스화한다
        // 다음은 그냥 결과만 돌려줌
        return List.of(
                new BadgeForTrainings(
                        Arrays.asList("TESTING", "CODE QUALITY"),
                        Badge.QUALITY_HERO),
                new BadgeForTrainings(
                        Arrays.asList("SECURITY 101", "SECURITY FOR MOBILE DEVS"),
                        Badge.SECURITY_COP)
        );
    }
}
