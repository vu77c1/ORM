package ma101;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ma101.dao.SeatDao;
import ma101.entities.Room;
import ma101.entities.RoomDetail;
import ma101.entities.Seat;
import ma101.enums.SeatStatus;
import ma101.enums.SeatType;
import ma101.ipl.SeatDaoipl;

public class SeatDaoTest {

    static SeatDao seatDao;

    @BeforeAll
    public static void init() {
        seatDao = new SeatDaoipl();
    }

    @Test
    public void testInsertSeat() {
        Seat seat = new Seat();
        seat.setColumn("10");
        seat.setRow(2);
        seat.setStatus(SeatStatus.BOOKED);
        seat.setType(SeatType.VIP);
        Room room = new Room();
        room.setId(4);
        seat.setRoom(room);
        assertEquals(true, seatDao.insertSeat(seat));
    }

    @Test
    public void testGetAllSeat() {

        List<Seat> seatList = seatDao.getAllSeat();
        assertEquals(true, seatList.size() > 0);
        for (Seat seat : seatList) {
            System.out.println("column: " + seat.getColumn());
            System.out.println("row: " + seat.getRow());
            System.out.println("status: " + seat.getStatus());
            System.out.println("type: " + seat.getType());
            System.out.println("room: " + seat.getRoom().getId());
        }
    }

    @Test
    public void testGetSeatByID() {
        Seat seat = seatDao.getSeatByID(1);
        assertEquals(1, seat.getId());
        System.out.println("column: " + seat.getColumn());
        System.out.println("row: " + seat.getRow());
        System.out.println("status: " + seat.getStatus());
        System.out.println("type: " + seat.getType());
        System.out.println("room: " + seat.getRoom().getId());
    }

    @Test
    public void testUpdateSeat() {
        Seat seat = seatDao.getSeatByID(1);
        seat.setStatus(SeatStatus.NOT_AVAILABLE);
        seat.setType(SeatType.VIP);
        assertEquals(true, seatDao.updateSeatByID(seat));

    }

    @Test
    public void testDeleteSeat() {
        assertEquals(true, seatDao.deleteSeatByID(1));

    }

}
