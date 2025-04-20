package ch2.v5;

import java.util.Optional;

public interface OfferingRepository {

    void save(Offering offering);
    void update(Offering offering);

    Offering getOfferingFrom(int enrollmentId);
}
