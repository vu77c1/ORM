package ma101;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ma101.dao.RoomDao;
import ma101.dao.RoomDetailDao;
import ma101.entities.Room;
import ma101.ipl.RoomDaoipl;

public class RoomDaoTest {
    static RoomDao roomDao;

    @BeforeAll
    public static void init() {
        roomDao = new RoomDaoipl();
    }

    @Test
    public void testGetAllRoom() {
        List<Room> allRooms = roomDao.getAllRoom();
        // assertNotNull(allRooms);
        // assertFalse(allRooms.isEmpty());
        // assertTrue(allRooms.size() > 0);
        // Gọi phương thức để lấy danh sách tất cả các phòng

        // In danh sách các phòng ra console
        System.out.println("Danh sách các phòng:");
        for (Room room : allRooms) {
            System.out.println("ID: " + room.getId());
            System.out.println("Tên phòng: " + room.getName());
            System.out.println("Số lượng ghế: " + room.getQuantity());
            System.out.println("-----------------------------------");
        }
    }

    @Test
    public void testGetRoomByID() {
        Room room = roomDao.getRoomByID(1);
        assertEquals(1, room.getId());
    }

    @Test
    public void testDeleteRoomByID() {
        assertEquals(true, roomDao.deleteRoomByID(3));
    }

    @Test

    public void testinsertRoom() {
        Room room = new Room();
        room.setName("testRoom");
        room.setQuantity(1);
        assertEquals(true, roomDao.insertRoom(room));
    }

}
