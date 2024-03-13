package ma101.dao;

import java.util.List;

import ma101.entities.Room;

public interface RoomDao {

    public boolean insertRoom(Room Room);

    public List<Room> getAllRoom();

    public boolean deleteRoomByID(int id);

    public Room getRoomByID(int id);

    public boolean updateRoomByID(Room room);

}
