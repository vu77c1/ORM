package finals.dao;

import java.util.List;

import finals.entities.DICH_VU;

public interface DichVuDao {

    void save(DICH_VU dv);

    DICH_VU findById(int id);

    void delete(DICH_VU dv);

    void update(DICH_VU dv);

    void insert(DICH_VU dv);

    List<DICH_VU> findAll();

}
