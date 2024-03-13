package ma101;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ma101.dao.RoomDetailDao;
import ma101.entities.Room;
import ma101.entities.RoomDetail;
import ma101.ipl.RoomDetailIDaoipl;

public class RoomDetailDaoTest {
    static RoomDetailDao roomDetailDao;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        roomDetailDao = new RoomDetailIDaoipl();
    }

    @Test
    void testInsertRoomDetail() {
        RoomDetail roomDetail = new RoomDetail();
        Room room = new Room();
        room.setId(1);

        roomDetail.setRate(200);
        roomDetail.setActiveDate(LocalDate.of(2022, 1, 1));
        roomDetail.setDescription("test");
        roomDetail.setRoom(room);

        assertEquals(true, roomDetailDao.insertRoomDetail(roomDetail));

    }

    @Test

    void testUpdateRoomDetail() {

        RoomDetail roomDetail = roomDetailDao.getRoomDetailByID(9);
        roomDetail.setRate(4000050);
        roomDetail.setActiveDate(LocalDate.of(2025, 1, 1));
        roomDetail.setDescription("test3006");
        assertEquals(true, roomDetailDao.updateRoomDetailByID(roomDetail));
    }

    @Test
    void testDeleteRoomDetail() {

        assertEquals(true, roomDetailDao.deleteRoomDetailByID(8));
    }

    @Test
    void testGetAllRoomDetail() {
        List<RoomDetail> roomDetails = roomDetailDao.getAllRoomDetail();
        assertEquals(9, roomDetails.get(0).getId());
    }

    @Test
    void testGetRoomDetailByID() {
        int roomDetailId = 9;
        RoomDetail roomDetail = roomDetailDao.getRoomDetailByID(roomDetailId);
        // So sánh giá trị thực tế với giá trị mong đợi
        assertEquals(roomDetailId, roomDetail.getId());
    }

}
