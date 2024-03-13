package slide.ipl;

import java.io.Serializable;
import java.util.List;
import org.hibernate.query.Query;

import slide.dao.JobDao;
import slide.entities.Jobs;
import slide.utils.HibernateUtils;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class JobDaoImpl implements JobDao {
    @Override
    public boolean save(Jobs job) throws Exception {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Serializable result = session.save(job);

            transaction.commit();

            return (result != null);

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public boolean delete(String jobId) throws Exception {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Jobs job = session.get(Jobs.class, jobId);
            if (job != null) {
                session.delete(job);
                transaction.commit();
                return true;
            } else {
                return false; // Không tìm thấy công việc có mã tương ứng
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(Jobs job) throws Exception {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.update(job); // Cập nhật thông tin của công việc

            transaction.commit();
            return true; // Trả về true nếu cập nhật thành công

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    // getAll
    @Override

    public Jobs getJob(String jobId) throws Exception {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.get(Jobs.class, jobId);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override

    public List<Jobs> getAll() throws Exception {
        Session session = null;

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Query<Jobs> query = session.createNativeQuery("SELECT * FROM dbo.Jobs", Jobs.class);
            return query.list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override

    public List<Jobs> findByNameAndSalary(String title, double salary) throws Exception {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Query<Jobs> query = session.createNativeQuery(
                    "SELECT * FROM dbo.Jobs WHERE job_title = :title AND min_salary <= :salary AND max_salary >= :salary",
                    Jobs.class);
            query.setParameter("title", title);
            query.setParameter("salary", salary);
            return query.list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
