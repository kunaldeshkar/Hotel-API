package com.hotelsapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hotelsapi.entity.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer>{

	@Query("SELECT a FROM Room a WHERE room_status != ?1")
	public List<Room> findRoomsByStatus(String status);
}
