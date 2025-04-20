package ch2.v4;

public class Employee {
    private String name;
    private String email;
    private String startingDate;
    private String role;
    private boolean wantsEmailUpdates;

    public Employee() {}

    public Employee(String name, String email, String startingDate, String role) {
        this.name = name;
        this.email = email;
        this.startingDate = startingDate;
        this.role = role;
    }

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
}
