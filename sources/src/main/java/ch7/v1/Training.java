package ch7.v1;

import java.time.LocalDate;

public class Training {
    private String name;
    private LocalDate endDate;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
}
