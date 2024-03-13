package finals.ipl;

import java.util.List;

import org.hibernate.Session;

import finals.dao.LSCSDao;
import finals.entities.LICH_SU_CHAM_SOC;
import finals.utils.HibernateUtils;

public class LSCSDaoIpl implements LSCSDao {

    @Override
    public List<LICH_SU_CHAM_SOC> findAll() {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.createQuery("from LICH_SU_CHAM_SOC", LICH_SU_CHAM_SOC.class).list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public LICH_SU_CHAM_SOC findById(int id) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.get(LICH_SU_CHAM_SOC.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(LICH_SU_CHAM_SOC lscs) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void update(LICH_SU_CHAM_SOC lscs) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(lscs);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void insert(LICH_SU_CHAM_SOC lscs) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(lscs);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
