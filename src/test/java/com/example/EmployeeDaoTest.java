package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import slide.dao.EmployeeDao;
import slide.entities.Employees;
import slide.entities.Jobs;
import slide.ipl.EmployeeDaoImpl;

public class EmployeeDaoTest {

    static EmployeeDao employeeDao;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        employeeDao = new EmployeeDaoImpl();
    }

    @Test
    void testSave() throws Exception {

        Employees employee = new Employees();
        employee.setId("E01");
        employee.setFirstName("Nguyen");
        employee.setLastName("Van A");
        employee.setEmail("a@a.com");
        employee.setPhoneNumber("1234567890");
        Jobs jobs = new Jobs();
        jobs.setJobId("J01");
        employee.setJobs(jobs);
        assertTrue(employeeDao.save(employee));
    }

}
