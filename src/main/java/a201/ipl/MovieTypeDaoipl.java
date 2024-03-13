package a201.ipl;

import java.util.List;

import org.hibernate.Session;

import a201.dao.MovieTypeDao;
import a201.entities.MovieType;
import a201.utils.HibernateUtils;

public class MovieTypeDaoipl implements MovieTypeDao {

    @Override
    public List<MovieType> findAll() {

        Session session = null;
        try {

            session = HibernateUtils.getSessionFactory().openSession();
            return session.createQuery("from MovieType").list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public MovieType findById(int id) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.get(MovieType.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void insert(MovieType movieType) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(movieType);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void update(MovieType movieType) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(movieType);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(MovieType movieType) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(movieType);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
