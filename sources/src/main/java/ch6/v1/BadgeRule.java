package ch6.v1;

public interface BadgeRule {
    boolean give(Employee employee);
    Badge badgeToGive();
}
