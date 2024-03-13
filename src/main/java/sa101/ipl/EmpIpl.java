package sa101.ipl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import sa101.HibernateUtils;
import sa101.dao.EmpDao;
import sa101.entities.Employee;
import slide.entities.Jobs;

public class EmpIpl implements EmpDao {

    @Override
    public Employee getEmployeeByID(int id) throws Exception {
        Session session = null;
        try {

            session = HibernateUtils.getSessionFactory().openSession();
            Employee employee = session.get(Employee.class, id);
            if (employee != null) {
                return employee;
            }

        } finally {
            if (session != null) {
                session.close();
            }
        }

        return null;
    }

    @Override
    public List<Employee> getAllEmployee() throws Exception {
        Session session = null;

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Query<Employee> query = session.createNativeQuery("SELECT * FROM dbo.employee", Employee.class);
            return query.list();

        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public boolean insertEmployee(Employee employee) throws Exception {
        Session session = null;

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.save(employee);
            return true;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean updateEmployeeByID(Employee employee) throws Exception {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
            return true;
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

    @Override
    public boolean deleteEmployeeByID(int id) throws Exception {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Employee employee = session.get(Employee.class, id);
            if (employee != null) {
                session.delete(employee);
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

}
