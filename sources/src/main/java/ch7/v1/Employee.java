package ch7.v1;

import java.util.Set;

public class Employee {
    private int id;
    private String name;
    private String email;
    private String startingDate;
    private String role;
    private boolean wantsEmailUpdates;
    // 참여했던 교육 목록은 Employee가 참여한 완료된 Enrollment로부터 얻을 수 있다
    private TrainingsTaken trainingsTaken;
    private Set<Badge> badges;

    public Employee() {}

    public Employee(int id, String name, String email, String startingDate, String role) {
        this.name = name;
        this.email = email;
        this.startingDate = startingDate;
        this.role = role;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean wantsAnyEmailUpdates() {
        return wantsEmailUpdates;
    }

    public void setWantsEmailUpdates(boolean wantsEmailUpdates) {
        this.wantsEmailUpdates = wantsEmailUpdates;
    }

    public TrainingsTaken getTrainingsTaken() {
        return trainingsTaken;
    }

    public void winBadge(Badge badge) {
        if (badges.contains(badge)) {
            throw new BadgeAlreadyExistsException();
        }
        badges.add(badge);
    }
}
