package la101.ipl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

import la101.dao.DocterDao;
import la101.entities.Docter;
import la101.utils.HibernateUtils;

public class DocterDaoipl implements DocterDao {

    public List<Docter> findAll() {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            @SuppressWarnings("unchecked")
            List<Docter> docters = session.createQuery("FROM Docter").list();
            return docters;
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public Docter findById(int id) {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Docter docter = session.get(Docter.class, id);
            return docter;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void insert(Docter docter) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(docter);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle exception appropriately
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void update(Docter docter) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(docter);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle exception appropriately
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public void delete(Docter docter) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(docter);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle exception appropriately
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
