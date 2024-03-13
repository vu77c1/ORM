package finals.dao;

import java.util.List;

import finals.entities.KHACH_HANG;

public interface KhachHangDao {

    void save(KHACH_HANG khachHang);

    KHACH_HANG findById(int id);

    void delete(KHACH_HANG khachHang);

    void update(KHACH_HANG khachHang);

    void insert(KHACH_HANG khachHang);

    List<KHACH_HANG> findAll();

    List<KHACH_HANG> findByLichSuChamSoc();

}
