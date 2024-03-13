package la101.dao;

import java.util.List;

import la101.entities.Bill;

public interface BillDao {

    void save(Bill bill);

    Bill findById(int id);

    void delete(Bill bill);

    void update(Bill bill);

    void insert(Bill bill);

    Bill insert_Id(Bill bill);

    List<Bill> findAll();

}
