package la101.dao;

import java.util.List;

import la101.entities.Patient;

public interface PatientDao {
    List<Patient> findAll();

    Patient findById(int id);

    void insert(Patient patient);

    void update(Patient patient);

    void delete(Patient patient);

}
