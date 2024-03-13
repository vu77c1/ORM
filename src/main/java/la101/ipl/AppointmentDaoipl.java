package la101.ipl;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.Transaction;

import la101.dao.AppointmentDao;
import la101.entities.Appointment;
import la101.utils.HibernateUtils;

public class AppointmentDaoipl implements AppointmentDao {

    @Override
    public List<Appointment> findAll() {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            List<Appointment> list = session.createQuery("from Appointment", Appointment.class).list();
            return list;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Appointment findById(int id) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Appointment appointment = session.get(Appointment.class, id);
            return appointment;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void insert(Appointment appointment) {

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(appointment);
            transaction.commit();
            System.out.println("Ban da insert thanh cong");
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
    // Add or update

    @Override
    public void save(Appointment appointment) {

        List<Appointment> appointments = findAll().stream()
                .filter(z -> z.getPatient().getId() == appointment.getPatient().getId()
                        && z.getDate().equals(appointment.getDate()))
                .collect(Collectors.toList());
        boolean hasConflict = findAll().stream()
                .anyMatch(a -> a.getDocter().getId() == appointment.getDocter().getId()
                        && a.getDate().equals(appointment.getDate())
                        && a.getTime().equals(appointment.getTime()));

        if (hasConflict) {
            throw new RuntimeException("Kham benh bi trung");
        } else {
            if (appointment.getDate().isBefore(LocalDate.now())) {
                throw new RuntimeException("Ngay kham benh phai lon hon ngay hien tai");

            } else {
                if (appointments.isEmpty()) {
                    insert(appointment);
                } else {
                    appointment.setId(appointments.get(0).getId());
                    update(appointment);
                }

            }

        }

    }

    @Override
    public void update(Appointment appointment) {

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(appointment);
            transaction.commit();
            System.out.println("Ban da update thanh cong");
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
    public void delete(Appointment appointment) {

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(appointment);
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
