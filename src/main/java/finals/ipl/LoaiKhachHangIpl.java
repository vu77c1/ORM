package finals.ipl;

import java.util.List;

import org.hibernate.Session;

import finals.dao.LoaiKhachHangDao;
import finals.entities.LOAI_KHACH_HANG;
import finals.utils.HibernateUtils;

public class LoaiKhachHangIpl implements LoaiKhachHangDao {

    @Override
    public void save(LOAI_KHACH_HANG loaiKhachHang) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public LOAI_KHACH_HANG findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public void delete(LOAI_KHACH_HANG loaiKhachHang) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void update(LOAI_KHACH_HANG loaiKhachHang) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void insert(LOAI_KHACH_HANG loaiKhachHang) {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(loaiKhachHang);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<LOAI_KHACH_HANG> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

}
