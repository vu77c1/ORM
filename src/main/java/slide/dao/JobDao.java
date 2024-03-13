package slide.dao;

import java.util.List;

import slide.entities.Jobs;

public interface JobDao {
    boolean save(Jobs job) throws Exception;

    boolean delete(String jobId) throws Exception;

    boolean update(Jobs job) throws Exception;

    Jobs getJob(String jobId) throws Exception;

    List<Jobs> getAll() throws Exception;

    List<Jobs> findByNameAndSalary(String title, double salary) throws Exception;

}
