package a301.ipl;

import java.util.List;

import org.hibernate.Session;

import a301.dao.InterviewDao;
import a301.entities.Interview;
import a301.utils.HibernateUtils;

public class InterviewDaoipl implements InterviewDao {

    @Override
    public List<Interview> findAll() {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.createQuery("from Interview", Interview.class).list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Interview findById(int id) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.get(Interview.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void insert(Interview interview) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(interview);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void update(Interview interview) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(interview);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public void delete(Interview interview) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(interview);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
