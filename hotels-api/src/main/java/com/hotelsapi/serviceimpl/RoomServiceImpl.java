package com.hotelsapi.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelsapi.entity.Room;
import com.hotelsapi.entity.User;
import com.hotelsapi.repository.RoomRepository;
import com.hotelsapi.repository.UserRepository;
import com.hotelsapi.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService{

	public static final String STATUS = "BOOKED";
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Room createRoom(Room room) {
		// TODO Auto-generated method stub
		return roomRepository.save(room);
	}

	@Override
	public Room getBooking(int userId) {
		
		User user = userRepository.findById(userId).orElse(null);
		
		List<Room> rooms = getRoom(STATUS);
		List<Room> userRoom = new ArrayList<>();
		Room room = new Room();
		System.out.println(rooms);
		
		if(rooms.size() !=0 || !rooms.isEmpty()) {
			if(user.getBonus_points() >= rooms.get(0).getRoom_price()) {
				rooms.get(0).setRoom_status("BOOKED");
				user.setRoom(userRoom);
				userRoom.add(rooms.get(0));
				userRepository.save(user);
				room = roomRepository.save(rooms.get(0));
				
			}else if(user.getBonus_points() <= rooms.get(0).getRoom_price()) {
				rooms.get(0).setRoom_status("PENDING APPROVAL");
				user.setRoom(userRoom);
				userRoom.add(rooms.get(0));
				userRepository.save(user);
				room = roomRepository.save(rooms.get(0));
				
			}
		}
		
		return room;
	}
	
	private List<Room> getRoom(String status) {
		List<Room> room = roomRepository.findRoomsByStatus(status);
		return room;
	}

}
