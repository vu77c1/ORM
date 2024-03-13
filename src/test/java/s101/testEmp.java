package s101;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sa101.dao.EmpDao;
import sa101.entities.Employee;
import sa101.ipl.EmpIpl;

public class testEmp {

    static EmpDao empDao;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        empDao = new EmpIpl();
    }

    @Test
    void testGetAllEmployee() throws Exception {
        List<Employee> list = empDao.getAllEmployee();
        for (Employee employee : list) {

            System.out.println(employee);
        }
    }

}
