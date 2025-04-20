package ch3.v1;

import java.time.LocalDate;
import java.util.Set;

class Offering {
    private int id;                          // 1
    private Training training;
    private LocalDate date;
    private Set<Employee> employees;
    private int maximumNumberOfAttendees;
    private int availableSpots;

    public Offering(
            Training training,
            LocalDate date,
            int maximumNumberOfAttendees,
            int availableSpots) {                       // 2
        this.training = training;
        this.date = date;
        this.maximumNumberOfAttendees = maximumNumberOfAttendees;
        this.availableSpots = maximumNumberOfAttendees;   // 3
    }

    public Set<Employee> getEmployees() {               // 4
        return this.employees;
    }

    public int getAvailableSpots() {                    // 5
        return this.availableSpots;
    }

    public void setAvailableSpots(int availableSpots) { // 6
        this.availableSpots = availableSpots;
    }
}