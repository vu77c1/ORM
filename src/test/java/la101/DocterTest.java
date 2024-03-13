package la101;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import la101.dao.DocterDao;
import la101.entities.Docter;
import la101.ipl.DocterDaoipl;

public class DocterTest {
    static DocterDao docterDao;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        docterDao = new DocterDaoipl();
    }

    @Test
    public void testFindAll() {
        List<Docter> docters = docterDao.findAll();
        assertNotNull(docters);
        assertFalse(docters.isEmpty());
        for (Docter docter : docters) {
            System.out.println(docter);
        }
    }

    @Test
    public void testFindById() {
        Docter docter = docterDao.findById(1);
        assertNotNull(docter);
        System.out.println(docter);
    }

    @Test
    public void testInsert() {
        Docter docter = new Docter();
        docter.setFirstName("Pham Xuan");
        docter.setLastName("Quy");
        docterDao.insert(docter);
        assertEquals(true, docter.getId() > 0);
    }

    @Test
    public void testUpdate() {
        Docter docter = docterDao.findById(1);
        docter.setFirstName("Pham Xuan");
        docter.setLastName("Lam");
        docterDao.update(docter);
        assertEquals(true, docter.getId() > 0);
    }

    @Test
    public void testDelete() {
        Docter docter = docterDao.findById(1);
        docterDao.delete(docter);
        assertEquals(true, docter.getId() > 0);
    }

}
