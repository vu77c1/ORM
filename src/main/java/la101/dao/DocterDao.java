package la101.dao;

import java.util.List;

import la101.entities.*;

public interface DocterDao {

    List<Docter> findAll();

    Docter findById(int id);

    void insert(Docter docter);

    void update(Docter docter);

    void delete(Docter docter);

}
