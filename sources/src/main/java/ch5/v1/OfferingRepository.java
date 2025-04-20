package ch5.v1;

import java.util.Optional;

public interface OfferingRepository {

    void save(Offering offering);
    void update(Offering offering);

    Optional<Offering> findById(int id);
    Offering getOfferingFrom(int enrollmentId);
}
