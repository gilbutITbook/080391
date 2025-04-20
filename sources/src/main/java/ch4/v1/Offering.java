package ch4.v1;

import java.time.LocalDate;
import java.util.List;

class OfferingSummary {
    private int id;
    private String training;
    private LocalDate date;
    private int numberOfEnrollments;
    private int maximumNumberOfAttendants;

    OfferingSummary(
            int id,
            String training,
            LocalDate date,
            int numberOfEnrollments,
            int maximumNumberOfAttendants) {
        this.id = id;
        this.training = training;
        this.date = date;
        this.numberOfEnrollments = numberOfEnrollments;
        this.maximumNumberOfAttendants = maximumNumberOfAttendants;
    }

    public static OfferingSummary convert(Offering offering) {
        return new OfferingSummary(
                offering.getId(),
                offering.getTraining().getName(),
                offering.getDate(),
                offering.getNumberOfEnrollments(),
                offering.getMaximumNumberOfAttendees()
        );
    }

    // 관련된 생성자, 게터, 세터

}

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

    public int getId() {
        return id;
    }

    public Training getTraining() {
        return training;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getNumberOfEnrollments() {
        return enrollments.size();
    }

    public int getMaximumNumberOfAttendees() {
        return maximumNumberOfAttendees;
    }

    public Boolean isEmployeeRegistered(Employee employee) { // 5
        return enrollments.stream()
                .anyMatch(enrollment -> enrollment.getEmployee().equals(employee));
    }
}