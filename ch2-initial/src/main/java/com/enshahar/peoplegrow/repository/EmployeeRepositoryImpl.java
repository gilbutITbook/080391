package com.enshahar.peoplegrow.repository;

import com.enshahar.peoplegrow.entity.Employee;
import com.enshahar.peoplegrow.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Override
    public Optional<Employee> findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Employee employee = session.createQuery("FROM Employee WHERE email = :email", Employee.class)
                    .setParameter("email", email)
                    .uniqueResult();
            return Optional.ofNullable(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void save(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
