package la101.dao;

import java.sql.Date;
import java.util.List;

import la101.entities.Appointment;

public interface AppointmentDao {

    List<Appointment> findAll();

    Appointment findById(int id);

    void insert(Appointment appointment);

    void update(Appointment appointment);

    void delete(Appointment appointment);

    void save(Appointment appointment);

}
