package a201;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import a201.dao.MovieDao;
import a201.entities.MoVie;
import a201.ipl.MovieDaoipl;

public class MovieDaoTest {
    static MovieDao movieDao;

    @BeforeAll
    public static void init() {
        movieDao = new MovieDaoipl();
    }

    @Test
    public void findAll() {
        List<MoVie> movies = movieDao.findAll();
        assertNotNull(movies);
        movies.forEach(movie -> System.out.println(movie));
    }

    @Test
    public void findById() {
        MoVie movie = movieDao.findById("MV001");
        assertNotNull(movie);
        System.out.println(movie);
    }

    @Test
    public void delete() {
        MoVie movie = movieDao.findById("MV001");
        movieDao.delete(movie);
    }

    @Test
    public void insert() {
        try {
            MoVie movie = new MoVie();
            movie.setId("MV005");
            movie.setActor("A");
            movie.setContent("A");
            movie.setDirector("B");
            movie.setDuration(2.5);
            movie.setFromDate(LocalDate.now());
            movie.setToDate(LocalDate.now().plusDays(3));
            movie.setMovieNameENG("Joker34");
            movie.setMovieNameVN("Joker 44");
            movieDao.insert(movie);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        MoVie movie = movieDao.findById("MV001");
        movie.setSmallImage("ABC");
        movieDao.update(movie);
    }

}
