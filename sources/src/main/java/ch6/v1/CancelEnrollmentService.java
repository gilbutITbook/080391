package ch6.v1;

import ch6.v2.EnrollmentRepository;

import static java.time.LocalDate.now;

public class CancelEnrollmentService {
    private OfferingRepository offerings;

    CancelEnrollmentService(OfferingRepository offerings) {
        this.offerings = offerings;
    }

    public void cancel(int offeringId, int employeeId) {  // 1
        if(offeringId<=0 || employeeId<=0)
            throw new IllegalArgumentException();
        Offering offering = offerings.getById(offeringId);
        Enrollment enrollment =
                offerings.getEnrollment(offeringId, employeeId);  // 2
        if(enrollment == null)
            throw new EnrollmentDoesntExistException();
        enrollment.cancel(now());
        offering.increaseAvailableSpots();                  // 3
    }
}
