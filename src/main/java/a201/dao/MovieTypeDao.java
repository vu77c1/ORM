package a201.dao;

import java.util.List;

import a201.entities.MovieType;

public interface MovieTypeDao {

    public List<MovieType> findAll();

    public MovieType findById(int id);

    public void insert(MovieType movieType);

    public void update(MovieType movieType);

    public void delete(MovieType movieType);

}
