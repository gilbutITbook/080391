package ch6.v1;

import java.util.LinkedList;
import java.util.List;

public class BadgeGiver {
    private final List<BadgeRule> rules;
    public BadgeGiver(BadgeForQuantityFactory quantityFactory, BadgeForTrainingsFactory trainingsFactory) {  // 1
        this.rules = new LinkedList<>();
        this.rules.addAll(quantityFactory.createRules());
        this.rules.addAll(trainingsFactory.createRules());
    }
    public void give(Employee employee) {
        for(BadgeRule rule : rules) {             // 2
            if(rule.give(employee)) {
                employee.winBadge(rule.badgeToGive());
            }
        }
    }
}

