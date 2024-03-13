package a301.dao;

import java.util.List;

import a301.entities.Interview;

public interface InterviewDao {

    List<Interview> findAll();

    Interview findById(int id);

    void insert(Interview interview);

    void update(Interview interview);

    void delete(Interview interview);

}
