package la101;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import la101.dao.AppointmentDao;
import la101.entities.Appointment;
import la101.entities.Docter;
import la101.entities.Patient;
import la101.ipl.AppointmentDaoipl;

public class AppointmentTest {
    static AppointmentDao appointmentDao;

    @BeforeAll
    public static void setUpBeforeClass() {
        appointmentDao = new AppointmentDaoipl();
    }

    @Test
    public void testFindAll() {
        List<Appointment> appointments = appointmentDao.findAll();
        assertNotNull(appointments);
        assertFalse(appointments.isEmpty());
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    @Test
    public void testFindById() {
        Appointment appointment = appointmentDao.findById(4);
        assertNotNull(appointment);
        System.out.println(appointment);
    }

    @Test
    public void testInsert() {
        Appointment appointment = new Appointment();
        appointment.setDate(LocalDate.of(2023, 2, 2));
        appointment.setTime(LocalTime.now());
        appointment.setDuration(30);
        appointment.setReason("test");
        Docter docter = new Docter();
        docter.setId(2);
        appointment.setDocter(docter);
        Patient patient = new Patient();
        patient.setId(1);
        appointment.setPatient(patient);
        appointment.setBill(null);
        appointmentDao.insert(appointment);
        assertEquals(true, appointment.getId() > 0);

    }

    @Test
    public void testUpdate() {
        Appointment appointment = appointmentDao.findById(4);
        appointment.setReason("test update");
        // appointmentDao.update(appointment);
    }

    @Test
    public void testDelete() {
        Appointment appointment = appointmentDao.findById(3);
        appointmentDao.delete(appointment);
    }

    @Test
    public void testSave() {
        Appointment appointment = new Appointment();
        appointment.setDate(LocalDate.of(2024, 2, 6));
        appointment.setTime(LocalTime.of(12, 15));
        appointment.setDuration(30);
        appointment.setReason("HHHH");
        Docter docter = new Docter();
        docter.setId(2);
        appointment.setDocter(docter);
        Patient patient = new Patient();
        patient.setId(1);
        appointment.setPatient(patient);
        appointment.setBill(null);
        appointmentDao.save(appointment);
        assertEquals(true, appointment.getId() > 0);
    }

}
