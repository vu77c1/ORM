package finals.ipl;

import java.util.List;

import org.hibernate.Session;

import finals.dao.NhanVienDao;
import finals.entities.NHAN_VIEN;
import finals.utils.HibernateUtils;

public class NhanVienDaoIpl implements NhanVienDao {

    @Override
    public List<NHAN_VIEN> findAll() {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.createQuery("from NHAN_VIEN", NHAN_VIEN.class).list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public NHAN_VIEN findById(int id) {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.get(NHAN_VIEN.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void insert(NHAN_VIEN nhanVien) {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(nhanVien);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void update(NHAN_VIEN nhanVien) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(NHAN_VIEN nhanVien) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
