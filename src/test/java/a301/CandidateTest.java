package a301;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import a301.dao.CandidateDao;
import a301.entities.Candidate;
import a301.enums.Gender;
import a301.ipl.CandidateDaoipl;

public class CandidateTest {
    static CandidateDao candidateDao;

    @BeforeAll
    public static void init() {
        candidateDao = new CandidateDaoipl();
    }

    @Test
    public void testFindAll() {
        try {
            List<Candidate> candidates = candidateDao.findAll();
            assertNotNull(candidates);
            candidates.forEach(candidate -> System.out.println(candidate));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindById() {
        try {
            Candidate candidate = candidateDao.findById(1);
            assertNotNull(candidate);
            System.out.println(candidate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        Candidate candidate = candidateDao.findById(5);
        candidateDao.delete(candidate);
    }

    @Test
    public void testUpdate() {
        try {

            Candidate candidate = candidateDao.findById(14);
            candidate.setPhone(null);
            candidate.setEmail(null);
            candidate.setCv(null);
            candidateDao.update(candidate);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsert() {
        try {
            // Tạo một đối tượng Candidate mới
            Candidate candidate = new Candidate();
            candidate.setFullName(null);
            candidate.setDateOfBirth(null);
            candidate.setGender(Gender.MALE);
            candidate.setGraduationYear(LocalDate.of(2015, 6, 30));
            candidate.setPhone("124e34354542");
            candidate.setEmail("johne.123@example.com");
            candidate.setSkill("Angluar");
            candidate.setForeignLanguage("English");
            candidate.setLevel(2); // Giả sử cấp độ là 5
            candidate.setCv("Path/to/CV.pdf");
            candidate.setAllocationStatus(0); // Giả sử trạng thái phân bổ là 0
            candidate.setRemark("No remark");
            // Gọi phương thức insert của candidateDao để chèn candidate vào cơ sở dữ liệu
            candidateDao.insert(candidate);
        } catch (Exception e) {
            // Xử lý các ngoại lệ có thể xảy ra, ví dụ: log lỗi, hiển thị thông báo lỗi...
            e.printStackTrace();
        }
    }

    @Test
    public void testFindAllBySkillAndLevel() {
        try {
            List<Candidate> candidates = candidateDao.findAllBySkillAndLevel("Angluar", 2);
            assertNotNull(candidates);
            candidates.forEach(candidate -> System.out.println(candidate));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void testFindAllByInterviewDate() {
        try {
            List<Candidate> candidates = candidateDao.findAllByInterviewDate(LocalDate.of(2020, 10, 15));
            assertNotNull(candidates);
            candidates.forEach(candidate -> System.out.println(candidate));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void testFindAllByForeignLanguageAndSkill() {
        try {
            List<Candidate> candidates = candidateDao.findAllByForeignLanguageAndSkill("English", "Angluar");
            assertNotNull(candidates);
            candidates.forEach(candidate -> System.out.println(candidate));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void testFindByInterviewDate() {
        try {
            List<Candidate> candidates = candidateDao.findAllByInterviewDate(LocalDate.of(2024, 3, 8));
            assertNotNull(candidates);
            candidates.forEach(candidate -> System.out.println(candidate));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // updateWithoutContactInfo

    @Test
    public void testUpdateWithoutContactInfo() {
        try {
            candidateDao.updateWithoutContactInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByPaging() {
        int pageNumber = 1; // Trang hiện tại
        int pageSize = 3; // Số lượng ứng viên trên mỗi trang
        try {
            List<Candidate> candidates = candidateDao.findByPaging(pageNumber, pageSize);
            assertNotNull(candidates);
            int startPosition = (pageNumber - 1) * pageSize;
            // Lấy sublist từ vị trí bắt đầu đến cuối danh sách hoặc đến vị trí cuối cùng
            // của trang hiện tại
            List<Candidate> pageCandidates = candidates.stream()
                    .skip(startPosition)
                    .limit(pageSize)
                    .collect(Collectors.toList());

            // In danh sách các phần ra console
            System.out.println("Danh sách các phần:");
            for (Candidate candidate : pageCandidates) {
                System.out.println("ID: " + candidate.getId());
                System.out.println("Tên: " + candidate.getFullName());
                System.out.println("Ngày sinh: " + candidate.getDateOfBirth());
                System.out.println("-----------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // findAllBySkillAndEntryTestResult

    @Test
    public void testFindAllBySkillAndEntryTestResult() {
        try {
            List<Candidate> candidates = candidateDao.findAllBySkillAndEntryTestResult("Angular", "PASS");
            assertNotNull(candidates);
            candidates.forEach(candidate -> System.out.println(candidate));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
