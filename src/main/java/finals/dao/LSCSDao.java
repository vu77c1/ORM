package finals.dao;

import java.util.List;

import finals.entities.LICH_SU_CHAM_SOC;

public interface LSCSDao {

    List<LICH_SU_CHAM_SOC> findAll();

    LICH_SU_CHAM_SOC findById(int id);

    void delete(LICH_SU_CHAM_SOC lscs);

    void update(LICH_SU_CHAM_SOC lscs);

    void insert(LICH_SU_CHAM_SOC lscs);

}
