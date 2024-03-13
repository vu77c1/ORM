package ma101.ipl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ma101.dao.RoomDao;
import ma101.entities.Room;
import ma101.utils.HibernateUtils;

public class RoomDaoipl implements RoomDao {

    @Override
    public boolean insertRoom(Room room) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(room);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle exception appropriately
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return false;

    }

    @Override
    public List<Room> getAllRoom() {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            @SuppressWarnings("unchecked")
            List<Room> roomList = session.createQuery("FROM Room").list();
            return roomList;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean deleteRoomByID(int id) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query<Room> query = session
                    .createNativeQuery("DELETE FROM dbo.room WHERE room_id = :id");
            query.setParameter("id", id);
            int deletedRows = query.executeUpdate();
            transaction.commit();
            return deletedRows > 0;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle exception appropriately
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return false;
    }

    @Override
    public Room getRoomByID(int id) {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Room room = session.get(Room.class, id);
            return room;
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public boolean updateRoomByID(Room room) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(room);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle exception appropriately
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return false;
    }

}
