package ch3.v3;

import java.time.LocalDate;
import java.util.Optional;

public class Enrollment {
    private Employee employee;
    private LocalDate dateOfEnrollment;
    private boolean status;
    private Optional<LocalDate> dateOfCancellation;

    public Enrollment(Employee employee, LocalDate dateOfEnrollment) {
        this.employee = employee;
        this.dateOfEnrollment = dateOfEnrollment;
        this.status = true;
        this.dateOfCancellation = Optional.empty();
    }

    public void cancel(LocalDate dateOfCancellation) {
        this.status = false;
        this.dateOfCancellation = Optional.of(dateOfCancellation);
    }

    public Object getEmployee() {
        return employee;
    }

    // getters

}
