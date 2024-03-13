package la101.ipl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import la101.dao.PatientDao;
import la101.entities.Patient;
import la101.utils.HibernateUtils;

public class PatientDaoipl implements PatientDao {

    @Override
    public List<Patient> findAll() {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            @SuppressWarnings("unchecked")
            List<Patient> patients = session.createQuery("FROM Patient").list();
            return patients;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Patient findById(int id) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Patient patient = session.get(Patient.class, id);
            return patient;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void insert(Patient patient) {

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(patient);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle exception appropriately
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void update(Patient patient) {

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(patient);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle exception appropriately
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Patient patient) {

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(patient);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle exception appropriately
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
