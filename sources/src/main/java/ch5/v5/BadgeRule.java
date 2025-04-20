package ch5.v5;

public interface BadgeRule {
    boolean give(Employee employee);
    Badge badgeToGive();
}
