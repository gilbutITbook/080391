package ch3.v2;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

public class Offering {
    private int id;
    private Training training;
    private LocalDate date;
    private Set<Employee> employees;
    private int maximumNumberOfAttendees;
    private int availableSpots;

    public Offering(
            Training training,
            LocalDate date,
            int maximumNumberOfAttendees) {
        this.training = training;
        this.date = date;
        this.maximumNumberOfAttendees = maximumNumberOfAttendees;
        this.availableSpots = maximumNumberOfAttendees;
    }

    public Set<Employee> getEmployees() {
        return Collections.unmodifiableSet(this.employees);
    }

    public void setAvailableSpots(int availableSpots) { // 6
        this.availableSpots = availableSpots;
    }

    public void addEmployee(Employee employee) throws OfferingIsFullException {
        if(availableSpots == 0)
            throw new OfferingIsFullException();

        employees.add(employee);
        availableSpots--;
    }

    public boolean hasAvailableSpots() {
        return availableSpots > 0;
    }

    public int getAvailableSpots() {
        return this.availableSpots;
    }

    public Training getTraining() {
        return training;
    }

    public boolean isEmployeeRegistered(Employee employee) {
        return employees.contains(employee);
    }
}