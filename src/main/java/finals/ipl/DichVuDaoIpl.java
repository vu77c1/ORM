package finals.ipl;

import java.util.List;

import org.hibernate.Session;

import finals.dao.DichVuDao;
import finals.entities.DICH_VU;
import finals.entities.KHACH_HANG;
import finals.utils.HibernateUtils;

public class DichVuDaoIpl implements DichVuDao {

    @Override
    public void save(DICH_VU dv) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public DICH_VU findById(int id) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.get(DICH_VU.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(DICH_VU dv) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void update(DICH_VU dv) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void insert(DICH_VU dv) {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(dv);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<DICH_VU> findAll() {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.createQuery("from DICH_VU", DICH_VU.class).list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
