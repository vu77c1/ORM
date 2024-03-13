package ma101.dao;

import java.util.List;

import ma101.entities.RoomDetail;

public interface RoomDetailDao {
    public RoomDetail getRoomDetailByID(int id);

    public boolean insertRoomDetail(RoomDetail roomDetail);

    public boolean updateRoomDetailByID(RoomDetail roomDetail);

    public boolean deleteRoomDetailByID(int id);

    public List<RoomDetail> getAllRoomDetail();

}
