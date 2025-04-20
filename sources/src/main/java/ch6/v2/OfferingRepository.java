package ch6.v2;

import java.util.Optional;

public interface OfferingRepository {
    void save(Offering offering);
    void update(Offering offering);

    Optional<Offering> findById(int id);
    Offering getById(int id);
    Offering getOfferingFrom(int enrollmentId);

    Enrollment getEnrollment(int offeringId, int employeeId);

    int availableSpots(Offering offering);
}
