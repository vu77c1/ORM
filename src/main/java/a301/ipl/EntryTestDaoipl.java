package a301.ipl;

import java.util.List;

import a301.dao.EntryTestDao;
import a301.entities.EntryTest;
import a301.utils.HibernateUtils;
import org.hibernate.Session;

public class EntryTestDaoipl implements EntryTestDao {

    @Override
    public List<EntryTest> findAll() {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.createQuery("from EntryTest", EntryTest.class).list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public EntryTest findById(int id) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.get(EntryTest.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void insert(EntryTest entryTest) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(entryTest);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void update(EntryTest entryTest) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(entryTest);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(EntryTest entryTest) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(entryTest);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
