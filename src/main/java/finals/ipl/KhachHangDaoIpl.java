package finals.ipl;

import java.util.List;

import org.hibernate.Session;

import finals.dao.KhachHangDao;
import finals.entities.KHACH_HANG;
import finals.utils.HibernateUtils;

public class KhachHangDaoIpl implements KhachHangDao {

    @Override
    public void save(KHACH_HANG khachHang) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public KHACH_HANG findById(int id) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.get(KHACH_HANG.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(KHACH_HANG khachHang) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void update(KHACH_HANG khachHang) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void insert(KHACH_HANG khachHang) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(khachHang);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    // hiển thị các khách hàng chưa có thông tin trong bảng LICH_SU_CHAM_SOC

    @Override
    public List<KHACH_HANG> findAll() {

        // hiển thị các khách hàng chưa có thông tin trong bảng LICH_SU_CHAM_SOC

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.createQuery("from KHACH_HANG", KHACH_HANG.class).list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<KHACH_HANG> findByLichSuChamSoc() {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session
                    .createQuery(
                            "SELECT c FROM KHACH_HANG c WHERE c.id NOT IN (SELECT lscs.khachHang.id FROM LICH_SU_CHAM_SOC lscs)",
                            KHACH_HANG.class)
                    .getResultList();
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

}
