package ma101.app;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;

import ma101.dao.RoomDetailDao;
import ma101.entities.RoomDetail;
import ma101.ipl.RoomDetailIDaoipl;
import ma101.utils.HibernateUtils;

public class app {
    public static void main(String[] args) {
        connectDb();

    }

    private static void connectDb() {
        // try (Session session = HibernateUtils.getSessionFactory().openSession()) {
        // if (session != null) {

        // System.out.println("Connected to the database successfully.");

        // }
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        RoomDetailDao roomDetailDao = new RoomDetailIDaoipl();
        for (RoomDetail roomDetail : roomDetailDao.getAllRoomDetail()) {
            System.out.println(roomDetail.toString());

        }
    }

}
