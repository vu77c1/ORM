package a201.ipl;

import java.util.List;

import org.hibernate.Session;

import a201.dao.MovieDao;
import a201.entities.MoVie;
import a201.utils.HibernateUtils;

public class MovieDaoipl implements MovieDao {

    @Override
    public List<MoVie> findAll() {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.createQuery("from MoVie").list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public MoVie findById(String id) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.get(MoVie.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void insert(MoVie movie) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(movie);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void update(MoVie movie) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(movie);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(MoVie movie) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(movie);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
