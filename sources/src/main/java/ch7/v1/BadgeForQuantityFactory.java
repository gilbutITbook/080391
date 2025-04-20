package ch7.v1;

import java.util.List;

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

public class BadgeForQuantityFactory implements BadgeRuleFactory {
    public List<BadgeRule> createRules() {
        // DB에 접근해 데이터를 얻고,
        // 각각의 데이터에 대해 BadgeForQuantity 클래스를 인스턴스화한다
        // 다음은 그냥 결과만 돌려줌
        return List.of(
                new BadgeForQuantity(5, Badge.FIVE_TRAININGS),
                new BadgeForQuantity(10, Badge.TEN_TRAININGS)
        );
    }
}
