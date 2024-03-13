package la101.dao;

import java.util.List;

import la101.entities.Payment;

public interface PaymentDao {

    void save();

    void delete();

    void update(Payment payment);

    void insert(Payment payment);

    List<Payment> findAll();

    void findById(int id);

}
