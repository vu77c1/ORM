package a301.dao;

import java.util.List;

import a301.entities.EntryTest;

public interface EntryTestDao {

    List<EntryTest> findAll();

    EntryTest findById(int id);

    void insert(EntryTest entryTest);

    void update(EntryTest entryTest);

    void delete(EntryTest entryTest);

}
