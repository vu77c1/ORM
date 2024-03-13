package slide.example;

import java.util.List;

import slide.dao.JobDao;
import slide.entities.Jobs;
import slide.ipl.JobDaoImpl;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws Exception {
        JobDao jobDao = new JobDaoImpl();
        for (Jobs job : jobDao.getAll()) {
            System.out.println(job);

        }

    }
}
