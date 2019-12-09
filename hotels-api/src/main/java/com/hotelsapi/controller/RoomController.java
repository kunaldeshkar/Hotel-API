package com.hotelsapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelsapi.entity.Room;
import com.hotelsapi.service.RoomService;
import com.hotelsapi.util.PathProxy;

@RestController
@RequestMapping(value = { PathProxy.HOTEL_API })
public class RoomController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private RoomService roomService;
	
	@PostMapping("/rooms")
	public ResponseEntity<Object> createRoom(@RequestBody Room room){
		
		Room rooms = roomService.createRoom(room);
		return ResponseEntity.ok(rooms);
	}
	
	@GetMapping("/booking/{id}")
	public ResponseEntity<Object> getBooking(@PathVariable(value = "id") String userId) {
		
		Room room = roomService.getBooking(Integer.parseInt(userId));
		return ResponseEntity.ok(room);
	}
	
}
