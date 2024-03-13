package a301.app;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import a301.dao.CandidateDao;
import a301.entities.Candidate;
import a301.entities.Interview;
import a301.enums.Gender;
import a301.ipl.CandidateDaoipl;
import a301.utils.HibernateUtils;

public class app {
    static CandidateDao candidateDao = new CandidateDaoipl();

    public static void main(String[] args) {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
            Root<Interview> root = criteria.from(Interview.class);

            Join<Interview, Candidate> candidateJoin = root.join("candidate");
            criteria.multiselect(root, candidateJoin);

            List<Tuple> resultList = session.createQuery(criteria).getResultList();

            for (Tuple tuple : resultList) {
                Interview interview = (Interview) tuple.get(0, Interview.class);
                Candidate candidate = (Candidate) tuple.get(1, Candidate.class);

                System.out.println("Interview ID: " + interview.getId());
                System.out.println("Interview Date: " + interview.getDate());
                System.out.println("Candidate ID: " + candidate.getId());
                System.out.println("Candidate Name: " + candidate.getFullName());
                // Print other properties of Interview and Candidate as needed
            }

        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    private static void connectDb() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            if (session != null) {

                System.out.println("Connected to the database successfully.");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
