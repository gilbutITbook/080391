package ch2.v6;

public interface OfferingRepository {

    void save(Offering offering);
    void update(Offering offering);

    Offering getOfferingFrom(int enrollmentId);
}
