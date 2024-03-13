package a301.ipl;

import a301.dao.CandidateDao;
import a301.utils.HibernateUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;

import a301.dao.CandidateDao;
import a301.entities.Candidate;
import a301.entities.Interview;
import a301.enums.Gender;

public class CandidateDaoipl implements CandidateDao {

    @Override
    public List<Candidate> findAll() {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.createQuery("from Candidate", Candidate.class).list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Candidate findById(int id) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.get(Candidate.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void insert(Candidate candidate) {
        Configuration<?> config = Validation.byDefaultProvider().configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        Validator validator = factory.getValidator();
        Session session = null;
        try {
            displayViolationsIfAny(validator.validate(candidate));
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(candidate);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private <T> void displayViolationsIfAny(Set<ConstraintViolation<T>> violations) {
        if (violations.isEmpty()) {
            System.out.println(" information is valid");
            return;
        }
        System.out.println("information is invalid");
        for (ConstraintViolation<T> violation : violations) {
            System.out.println(" --- " + violation.getMessage());
        }
    }

    @Override
    public void update(Candidate candidate) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(candidate);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Candidate candidate) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(candidate);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    // a) Find all of the candidate that has skill is 'Angluar’ and skill level is
    // 2.

    @Override
    public List<Candidate> findAllBySkillAndLevel(String skill, int level) {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.createQuery("from Candidate where skill = :skill and level = :level", Candidate.class)
                    .setParameter("skill", skill).setParameter("level", level).list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    // b) Find all of the candidate that has foreign language is 'Japanese' and
    // skill is 'Python/ML'.

    @Override
    public List<Candidate> findAllByForeignLanguageAndSkill(String foreignLanguage, String skill) {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session
                    .createQuery("from Candidate where foreignLanguage = :foreignLanguage and skill = :skill",
                            Candidate.class)
                    .setParameter("foreignLanguage", foreignLanguage).setParameter("skill", skill).list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    // c) Find all of the candidate by skill and entry test result (that has skill
    // is ‘Java’ and pass entry test on '1-Oct-2020').

    @Override
    public List<Candidate> findAllBySkillAndEntryTestResult(String skill, String entryTestResult) {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            String hql = "select c from Candidate c join c.entryTests et where c.skill = :skill and et.result = :entryTestResult";
            @SuppressWarnings("unchecked")
            List<Object[]> results = session.createQuery(hql).setParameter("skill", skill)
                    .setParameter("entryTestResult", entryTestResult).getResultList();
            System.out.println(results);
            return results.stream().map(r -> (Candidate) r[0]).toList();
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    // d) Find all of the candidate that pass interview on '15-Oct-2020'.Criteria
    // Queries
    // are used.
    @Override
    public List<Candidate> findAllByInterviewDate(LocalDate interviewDate) {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Candidate> criteria = builder.createQuery(Candidate.class);
            Root<Candidate> root = criteria.from(Candidate.class);
            criteria.select(root);
            criteria.where(builder.equal(root.join("interviews").get("date"), interviewDate));
            criteria.where(builder.equal(root.join("interviews").get("result"), "Pass"));
            return session.createQuery(criteria).getResultList();

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private List<Interview> get() {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Interview> criteria = builder.createQuery(Interview.class);
            Root<Interview> root = criteria.from(Interview.class);
            criteria.select(root);
            Join<Interview, Candidate> candidateJoin = root.join("candidate");
            // criteria.select(builder.array(root, candidateJoin));

            return session.createQuery(criteria).getResultList();

        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public void updateWithoutContactInfo() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaUpdate<Candidate> criteriaUpdate = builder.createCriteriaUpdate(Candidate.class);
            Root<Candidate> root = criteriaUpdate.from(Candidate.class);

            // Set the value of the remark field to "inactive"
            criteriaUpdate.set("remark", "inactive");

            criteriaUpdate.where(
                    builder.and(
                            builder.isNull(root.get("phone")),
                            builder.isNull(root.get("email")),
                            builder.isNull(root.get("cv"))));
            int rowsUpdated = session.createQuery(criteriaUpdate).executeUpdate();

            if (rowsUpdated > 0) {
                transaction.commit();
                System.out.println("Number of candidates with remark updated: " + rowsUpdated);

            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<Candidate> findByPaging(int pageNumber, int pageSize) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Candidate> criteria = builder.createQuery(Candidate.class);
            Root<Candidate> root = criteria.from(Candidate.class);
            criteria.select(root);
            Query query = session.createQuery(criteria);
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);

            return query.getResultList();
        } finally {
            session.close();
        }
    }

    @Override
    public int getTotalPages(int pageSize) {
        int totalCandidates = findAll().size();

        // Tính toán và trả về tổng số trang
        return (int) Math.ceil((double) totalCandidates / pageSize);
    }

    @Override

    public List<Candidate> getGender() {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            return session.createQuery("from Candidate where gender = :gender", Candidate.class)
                    .setParameter("gender", Gender.FEMALE.getValue()).list();

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
