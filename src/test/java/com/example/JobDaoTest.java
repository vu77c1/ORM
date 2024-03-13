package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import slide.dao.EmployeeDao;
import slide.dao.JobDao;
import slide.entities.Jobs;
import slide.ipl.JobDaoImpl;

public class JobDaoTest {
    static JobDao jobDao;
    static EmployeeDao employeeDao;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        jobDao = new JobDaoImpl();
    }

    @Test
    void testSave2() throws Exception {
        Jobs job = new Jobs("J043", "Java De3v5", 1200, 2200);
        assertEquals(true, jobDao.save(job));
    }

    @Test
    void testDelete() throws Exception {
        assertEquals(true, jobDao.delete("J0243"));
    }

    @Test
    void testUpdate() throws Exception {
        Jobs job = new Jobs("J02", "Java Dev77", 12900, 22900);
        assertEquals(true, jobDao.update(job));
    }

    @Test
    void testGetJob() throws Exception {
        Jobs job = jobDao.getJob("J01");
        assertNotNull(job); // Kiểm tra xem công việc có tồn tại không
        assertEquals("J01", job.getJobId());
        System.out.println(job.getMaxSalary());// Kiểm tra xem jobId của công việc có đúng không
    }
    // @Test testSaveEm

}
