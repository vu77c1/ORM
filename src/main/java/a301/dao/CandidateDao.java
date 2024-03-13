package a301.dao;

import java.time.LocalDate;
import java.util.List;

import a301.entities.Candidate;

public interface CandidateDao {

    List<Candidate> findAll();

    Candidate findById(int id);

    void insert(Candidate candidate);

    void update(Candidate candidate);

    void delete(Candidate candidate);

    List<Candidate> findAllBySkillAndLevel(String skill, int level);

    List<Candidate> findAllByForeignLanguageAndSkill(String foreignLanguage, String skill);

    List<Candidate> findAllBySkillAndEntryTestResult(String skill, String entryTestResult);

    List<Candidate> findAllByInterviewDate(LocalDate interviewDate);

    void updateWithoutContactInfo();

    List<Candidate> findByPaging(int pageNumber, int pageSize);

    int getTotalPages(int pageSize);

    public List<Candidate> getGender();

}
