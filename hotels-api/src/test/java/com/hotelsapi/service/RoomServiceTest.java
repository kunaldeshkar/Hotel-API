package com.hotelsapi.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hotelsapi.entity.Room;
import com.hotelsapi.repository.RoomRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomServiceTest {

	@Autowired
	private RoomService roomService;
	
	@MockBean
	private RoomRepository roomRepository;
	
	@Test
	public void testCreateRoom(){

		Room room = new Room(1, 101, "BOOKED", 1500);
	    Mockito.when(roomRepository.save(room)).thenReturn(room);
	    assertThat(roomService.createRoom(room)).isEqualTo(room);
	}
	
	@Test
	public void testGetBooking() {
		Room room = new Room(1, 101, "AVAILABLE", 1500);
		Room expectedRoom = new Room(1, 101, "BOOKED", 1500);
		List<Room> actualRooms = new ArrayList<>();
		actualRooms.add(room);
		
		List<Room> expectedRooms = new ArrayList<>();
		expectedRooms.add(expectedRoom);
		
		Mockito.when(roomRepository.findRoomsByStatus("AVAILABLE")).thenReturn(actualRooms);
		
		System.out.println(roomService.getBooking(1).getRoom_id());
		assertThat(roomService.getBooking(1)).isEqualTo(expectedRooms);
	}
	
}
