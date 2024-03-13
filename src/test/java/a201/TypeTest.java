package a201;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import a201.dao.TypeDao;
import a201.entities.Type;
import a201.ipl.TypeDaoipl;

public class TypeTest {
    static TypeDao typeDao;

    @BeforeAll
    public static void init() {
        typeDao = new TypeDaoipl();
    }

    @Test
    public void findAll() {
        List<Type> types = typeDao.findAll();
        assertNotNull(types);
        types.forEach(type -> System.out.println(type));
    }

    @Test
    public void findById() {
        Type type = typeDao.findById(5);
        assertNotNull(type);
        System.out.println(type);
    }

    @Test
    public void insert() {
        Type type = new Type();
        type.setDescription("Hanh Dong");
        type.setName("Hanhs Dong");
        typeDao.insert(type);

    }

    @Test
    public void update() {
        Type type = typeDao.findById(1);
        type.setDescription("Phieu luu");
        typeDao.update(type);
    }

    @Test
    public void delete() {
        Type type = typeDao.findById(5);
        typeDao.delete(type);
    }

}
