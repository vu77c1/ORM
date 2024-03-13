package a301;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import a301.dao.CandidateDao;
import a301.dao.EntryTestDao;
import a301.entities.Candidate;
import a301.entities.EntryTest;
import a301.enums.ResutlType;
import a301.ipl.CandidateDaoipl;
import a301.ipl.EntryTestDaoipl;

public class EntryTestTest {
    static EntryTestDao entryTestDao;
    static CandidateDao candidateDao;

    @BeforeAll
    public static void init() {
        entryTestDao = new EntryTestDaoipl();
        candidateDao = new CandidateDaoipl();
    }

    @Test
    public void testFindAll() {
        try {
            List<EntryTest> entryTests = entryTestDao.findAll();
            assertNotNull(entryTests);
            entryTests.forEach(entryTest -> System.out.println(entryTest));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindById() {
        try {
            EntryTest entryTest = entryTestDao.findById(1);
            assertNotNull(entryTest);
            System.out.println(entryTest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        try {
            EntryTest entryTest = entryTestDao.findById(3);
            entryTest.setCandidate(candidateDao.findById(7));
            entryTestDao.update(entryTest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsert() {
        try {
            EntryTest entryTest = new EntryTest();
            entryTest.setTime("09:00"); // Set thời gian
            entryTest.setDate(LocalDate.now()); // Set ngày
            entryTest.setLanguageValuator("Evaluator Name"); // Set tên người đánh giá ngôn ngữ
            entryTest.setLanguageResult(9.5); // Set kết quả ngôn ngữ
            entryTest.setTechnicalValuator("Technical Evaluator"); // Set tên người đánh giá kỹ thuật
            entryTest.setTechnicalResult(8.7); // Set kết quả kỹ thuật
            entryTest.setRemark("Some remarks"); // Set ghi chú
            entryTest.setResult(ResutlType.PASS.toString()); // Set kết quả
            entryTest.setEntryTestSkill("Technical Skills"); // Set kỹ năng kiểm tra
            entryTest.setCandidate(candidateDao.findById(1)); // Set ứng viên (sử dụng candidateDao để tìm ứng viên với
                                                              // id là 1)
            entryTestDao.insert(entryTest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            EntryTest entryTest = entryTestDao.findById(1);
            entryTestDao.delete(entryTest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
