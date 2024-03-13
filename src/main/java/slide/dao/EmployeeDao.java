package slide.dao;

import slide.entities.Employees;

public interface EmployeeDao {

    public boolean save(Employees employee) throws Exception;

}
