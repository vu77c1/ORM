package a201;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import a201.dao.MovieTypeDao;
import a201.entities.MoVie;
import a201.entities.MovieType;
import a201.entities.Type;
import a201.ipl.MovieTypeDaoipl;

public class MovieTypeDaoTest {
    static MovieTypeDao movieTypeDao;

    @BeforeAll
    public static void init() {
        movieTypeDao = new MovieTypeDaoipl();
    }

    @Test
    public void findAll() {
        List<MovieType> list = movieTypeDao.findAll();
        assertNotNull(list);
        list.forEach(movieType -> System.out.println(movieType));
    }

    @Test
    public void findById() {
        MovieType movieType = movieTypeDao.findById(1);
        assertNotNull(movieType);
        System.out.println(movieType);
    }

    @Test
    public void delete() {
        MovieType movieType = movieTypeDao.findById(1);
        movieTypeDao.delete(movieType);
    }

    @Test
    public void insert() {
        MovieType movieType = new MovieType();

        Type type = new Type();
        type.setId(1);
        movieType.setType(type);

        MoVie movie = new MoVie();
        movie.setId("MV001");
        movieType.setMovie(movie);

        movieType.setDescription("abc");

        movieTypeDao.insert(movieType);

    }

    @Test
    public void update() {
        MovieType movieType = movieTypeDao.findById(1);
        movieType.setDescription("xyz");
        movieTypeDao.update(movieType);
    }

}
