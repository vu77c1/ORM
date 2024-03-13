package a301;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import a301.dao.InterviewDao;
import a301.entities.Candidate;
import a301.entities.Interview;
import a301.ipl.InterviewDaoipl;

public class InterviewTest {
    static InterviewDao interviewDao;

    @BeforeAll
    public static void init() {
        interviewDao = new InterviewDaoipl();
    }

    @Test
    public void testFindAll() {
        try {
            List<Interview> interviews = interviewDao.findAll();
            assertNotNull(interviews);
            interviews.forEach(interview -> System.out.println(interview));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindById() {
        try {
            Interview interview = interviewDao.findById(1);
            assertNotNull(interview);
            System.out.println(interview);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            Interview interview = interviewDao.findById(1);
            interviewDao.delete(interview);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsert() {
        try {
            Interview interview = new Interview();
            interview.setTime("10:00");
            interview.setDate(LocalDate.now());
            interview.setInterviewer("Interviewer Name");
            interview.setComments("Some comments");
            interview.setResult("Pass");
            interview.setRemark("Some remarks");

            // Set the Candidate for the Interview
            Candidate candidate = new Candidate();
            candidate.setId(1); // Assuming you have a candidate with ID 1 in the database
            interview.setCandidate(candidate);

            // Call the DAO method to insert the Interview
            interviewDao.insert(interview);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        try {
            Interview interview = interviewDao.findById(1);
            interview.setInterviewer("Java full stack");
            interviewDao.update(interview);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
