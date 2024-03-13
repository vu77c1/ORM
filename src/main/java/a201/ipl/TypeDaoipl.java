package a201.ipl;

import java.util.List;

import org.hibernate.Session;

import a201.dao.TypeDao;
import a201.entities.Type;
import a201.utils.HibernateUtils;

public class TypeDaoipl implements TypeDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Type> findAll() {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.createQuery("from Type").list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Type findById(int id) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.get(Type.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Type insert(Type type) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(type);
            session.getTransaction().commit();
            return type;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Type update(Type type) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(type);
            session.getTransaction().commit();
            return type;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Type type) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(type);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
