package finals.dao;

import java.util.List;

import finals.entities.NHAN_VIEN;

public interface NhanVienDao {

    List<NHAN_VIEN> findAll();

    NHAN_VIEN findById(int id);

    void insert(NHAN_VIEN nhanVien);

    void update(NHAN_VIEN nhanVien);

    void delete(NHAN_VIEN nhanVien);

}
