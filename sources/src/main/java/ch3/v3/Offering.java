package ch3.v3;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Offering {

    private int id;
    private Training training;
    private LocalDate date;
    private List<Enrollment> enrollments;                     // 1
    private int maximumNumberOfAttendees;
    private int availableSpots;

    public Offering(
            Training training,
            LocalDate date,
            int maximumNumberOfAttendees) {
        // 기본 생성자
        // ...
    }

    public void enroll(Employee employee) {                   // 2
        if(!hasAvailableSpots())
            throw new OfferingIsFullException();

        LocalDate now = LocalDate.now();
        enrollments.add(new Enrollment(employee, now));
        availableSpots--;
    }

    public void cancel(Employee employee) {                   // 3
        Enrollment enrollmentToCancel = findEnrollmentOf(employee);
        if(enrollmentToCancel == null)
            throw new EmployeeNotEnrolledException();

        LocalDate now = LocalDate.now();
        enrollmentToCancel.cancel(now);

        availableSpots++;
    }

    private Enrollment findEnrollmentOf(Employee employee) {  // 4
        // 등록 목록에 대해 루프를 돌면서
        // employee와 일치하는 Enrollment를 찾는다
        // ...

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getEmployee().equals(employee)) {
                return enrollment;
            }
        }
        return null;
    }

    public boolean hasAvailableSpots() {
        return availableSpots > 0;
    }

    public Training getTraining() {
        return training;
    }

    public Boolean isEmployeeRegistered(Employee employee) { // 5
        return enrollments.stream()
                .anyMatch(enrollment -> enrollment.getEmployee().equals(employee));
    }
}