package ch3.v1;

import java.util.List;

public interface OfferingRepository {

    void save(Offering offering);
    void update(Offering offering);

    Offering getOfferingFrom(int id);
}
