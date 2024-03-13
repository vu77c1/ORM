package sa101.app;

import java.util.List;

import org.hibernate.Session;

import sa101.HibernateUtils;
import sa101.dao.EmpDao;
import sa101.entities.Employee;
import sa101.ipl.EmpIpl;

public class app {

    public static void main(String[] args) throws Exception {
        EmpDao empDao = new EmpIpl();
        // connectDb();
        // getAllEmployee(empDao);
        // getEmployeeByID(empDao);
        // insertEmployee(empDao);
        // deleteEmployeeByID(empDao);
        // updateEmployeeByID(empDao);

    }

    private static void updateEmployeeByID(EmpDao empDao) throws Exception {
        Employee employee = empDao.getEmployeeByID(2);
        employee.setFirstName("Kha");
        employee.setLastName("My");
        boolean result = empDao.updateEmployeeByID(employee);
        if (result) {
            System.out.println("Update successfully");
            getAllEmployee(empDao);
        } else {
            System.out.println("Update failed");
        }
    }

    private static void deleteEmployeeByID(EmpDao empDao) throws Exception {
        boolean result = empDao.deleteEmployeeByID(1);
        if (result) {
            System.out.println("Delete successfully");
            getAllEmployee(empDao);

        } else {
            System.out.println("Delete failed");
        }
    }

    private static void insertEmployee(EmpDao empDao) throws Exception {
        Employee employee = new Employee();
        employee.setFirstName("Xuan");
        employee.setLastName("Phu");
        boolean result = empDao.insertEmployee(employee);
        if (result) {
            System.out.println("Insert successfully");
            getAllEmployee(empDao);
        } else {
            System.out.println("Insert failed");
        }
    }

    private static void getEmployeeByID(EmpDao empDao) throws Exception {
        Employee employee = empDao.getEmployeeByID(1);
        System.out.println(employee);
    }

    private static void getAllEmployee(EmpDao empDao) throws Exception {
        List<Employee> list = empDao.getAllEmployee();
        for (Employee employee : list) {

            System.out.println(employee);
        }
    }

    private static void connectDb() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            if (session != null) {

                System.out.println("Connected to the database successfully.");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
