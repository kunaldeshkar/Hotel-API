package com.hotelsapi.service;

import com.hotelsapi.entity.Room;

public interface RoomService {

	Room createRoom(Room room);

	Room getBooking(int userId);

}
