package sa101.dao;

import java.util.List;

import sa101.entities.Employee;

public interface EmpDao {
    public Employee getEmployeeByID(int id) throws Exception;

    public List<Employee> getAllEmployee() throws Exception;

    public boolean insertEmployee(Employee employee) throws Exception;

    public boolean updateEmployeeByID(Employee employee) throws Exception;

    public boolean deleteEmployeeByID(int id) throws Exception;

}
