package ma101.ipl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ma101.dao.SeatDao;
import ma101.entities.RoomDetail;
import ma101.entities.Seat;
import ma101.utils.HibernateUtils;

public class SeatDaoipl implements SeatDao {
    @Override

    public boolean insertSeat(Seat seat) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(seat);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle exception appropriately
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Seat> getAllSeat() {

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            @SuppressWarnings("unchecked")
            List<Seat> seatList = session.createQuery("FROM Seat").list();
            return seatList;
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public Seat getSeatByID(int id) {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Seat seat = session.get(Seat.class, id);
            return seat;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean updateSeatByID(Seat seat) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(seat);
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
    public boolean deleteSeatByID(int id) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query<Seat> query = session
                    .createNativeQuery("DELETE FROM dbo.seat WHERE seat_id = :id");
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

}
