package ma101.dao;

import java.util.List;

import ma101.entities.Seat;

public interface SeatDao {
    public boolean insertSeat(Seat seat);

    public List<Seat> getAllSeat();

    public Seat getSeatByID(int id);

    public boolean updateSeatByID(Seat seat);

    public boolean deleteSeatByID(int id);

}
