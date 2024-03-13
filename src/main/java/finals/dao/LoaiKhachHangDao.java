package finals.dao;

import java.util.List;

import finals.entities.LOAI_KHACH_HANG;

public interface LoaiKhachHangDao {

    void save(LOAI_KHACH_HANG loaiKhachHang);

    LOAI_KHACH_HANG findById(int id);

    void delete(LOAI_KHACH_HANG loaiKhachHang);

    void update(LOAI_KHACH_HANG loaiKhachHang);

    void insert(LOAI_KHACH_HANG loaiKhachHang);

    List<LOAI_KHACH_HANG> findAll();

}
