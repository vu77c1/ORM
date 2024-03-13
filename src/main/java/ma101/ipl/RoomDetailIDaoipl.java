package ma101.ipl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ma101.dao.RoomDetailDao;
import ma101.entities.RoomDetail;
import ma101.utils.HibernateUtils;

public class RoomDetailIDaoipl implements RoomDetailDao {

    @Override
    public RoomDetail getRoomDetailByID(int id) {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            RoomDetail roomDetail = session.get(RoomDetail.class, id);
            return roomDetail;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean insertRoomDetail(RoomDetail roomDetail) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(roomDetail);
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
    public boolean updateRoomDetailByID(RoomDetail roomDetail) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(roomDetail);
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
    public boolean deleteRoomDetailByID(int id) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query<RoomDetail> query = session
                    .createNativeQuery("DELETE FROM dbo.room_detail WHERE room_detail_id = :id");
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
    public List<RoomDetail> getAllRoomDetail() {
        Session session = null;

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            @SuppressWarnings("unchecked")
            Query<RoomDetail> query = session
                    .createNativeQuery("SELECT * FROM dbo.room_detail")
                    .addEntity(RoomDetail.class);

            return query.list();
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

}
