package la101;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import la101.dao.PatientDao;
import la101.entities.Patient;
import la101.ipl.PatientDaoipl;

public class PatientTest {
    static PatientDao patientDao;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        patientDao = new PatientDaoipl();
    }

    @Test
    public void testFindById() {
        Patient patient = patientDao.findById(1);
        assertNotNull(patient);
        System.out.println(patient);
    }

    @Test
    public void testFindAll() {
        List<Patient> patients = patientDao.findAll();
        assertNotNull(patients);
        assertFalse(patients.isEmpty());
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }

    @Test
    public void testInsert() {
        Patient patient = new Patient();
        patient.setAddress("Hoai Nhon");
        patient.setCity("Binh Dinh");
        patient.setFirstName("Phan Van");
        patient.setLastName("Quy");
        patient.setState("Hoai Tan");
        patientDao.insert(patient);
        assertEquals(true, patient.getId() > 0);
    }

    @Test
    public void testUpdate() {
        Patient patient = patientDao.findById(2);
        patient.setFirstName("Phan An");
        patient.setLastName("Lam");
        patientDao.update(patient);
        assertEquals(true, patient.getId() > 0);
    }

    @Test
    public void testDelete() {
        Patient patient = patientDao.findById(2);
        patientDao.delete(patient);
        assertEquals(true, patient.getId() > 0);
    }

}
