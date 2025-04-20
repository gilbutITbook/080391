package ch6.v2;

import java.time.LocalDate;

public class CancelEnrollmentService {
    private OfferingRepository offerings;
    private Enrollment enrollment;

    CancelEnrollmentService(OfferingRepository offerings,
                            Enrollment enrollment) {
        this.offerings = offerings;
        this.enrollment = enrollment;
    }

    public void cancel(int offeringId, int employeeId) {
        if(offeringId<=0 || employeeId<=0)
            throw new IllegalArgumentException();
        Offering offering = offerings.getById(offeringId);
        if(offering == null)
            throw new OfferingDoesntExistException();
        Enrollment enrollment =
                offerings.getEnrollment(offeringId, employeeId);
        if(enrollment == null)
            throw new EnrollmentDoesntExistException();

        LocalDate now = LocalDate.now();
        enrollment.cancel(now);
    }
}
