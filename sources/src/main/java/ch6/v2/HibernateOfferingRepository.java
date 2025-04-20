package ch6.v2;

import org.hibernate.Session;

import java.util.Optional;

public class HibernateOfferingRepository implements OfferingRepository {
    final private Cache<Integer,Offering> cache = new Cache<>();
    private final Session session;

    public HibernateOfferingRepository(Session session) {
        this.session = session;
    }

    public void save(Offering offering) {
        session.persist(offering);
    }
    public void update(Offering offering) {
        session.merge(offering);
    }

    public Optional<Offering> findById(int id){
        return Optional.ofNullable(session.find(Offering.class, id));
    }
    public Offering getById(int id){
        return session.get(Offering.class, id);
    }
    public Offering getOfferingFrom(int enrollmentId){
        return session.createQuery("from Offering o inner join o.enrollments e where e.id = :enrollmentId", Offering.class)
                .setParameter("enrollmentId", enrollmentId)
                .getSingleResult();
    }

    public Enrollment getEnrollment(int offeringId, int employeeId) {
        return session.createQuery("select e from Enrollment e where e.offering.id = :offeringId and e.employee.id = :employeeId", Enrollment.class)
                .setParameter("offeringId", offeringId)
                .setParameter("employeeId", employeeId)
                .getSingleResult();
    }

    public int availableSpots(Offering offering) {
        if(!cache.contains(offering)) {
            int spots = (int) session
                    .createQuery("select maximumNumberOfAttendees - " +
                            "count(...) from Offering o where ...")
                    .setParameter("offeringId", offering.getId())
                    .getSingleResult();
            cache.add(offering, spots);
        }
        return cache.get(offering).iterator().next();
    }
}
