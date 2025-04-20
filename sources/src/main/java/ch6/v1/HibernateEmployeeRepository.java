package ch6.v1;

import java.util.Optional;
import java.util.Set;
import org.hibernate.Session;

public class HibernateEmployeeRepository implements EmployeeRepository {
    final private Cache<Employee,String> cache = new Cache<>();
    private Session session;

    public HibernateEmployeeRepository(Session session) {
        this.session = session;
    }

    public Employee findById(int id) {
            return session.find(Employee.class, id);
    }

    public Set<Employee> findByLastName(String lastName) { // 2
        if (!cache.contains(lastName)) {
            cache.add(lastName,
                Set.copyOf(session
                    .createQuery("from Employee e where e.lastName = :lastName", Employee.class)
                    .setParameter(":lastName", lastName)
                    .getResultList())
            );
        }
        return cache.get(lastName);
    }

    public Optional<Employee> findByEmail(String email) {
        return Optional.ofNullable(
            session
                .createQuery("from Employee e where e.email = :email", Employee.class)
                .setParameter(":email", email)
                .getSingleResultOrNull()
        );
    }

    public void save(Employee employee) {
        session.persist(employee);
    }
    public void update(Employee employee) {
        session.merge(employee);
    }
}