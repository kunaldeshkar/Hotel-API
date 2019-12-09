package com.hotelsapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "room")
public class Room {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int room_id;
	private int room_number;
	private String room_status;
	private long room_price;
	
	public Room() {
		
	}
	
	public Room(int room_id, int room_number, String room_status, long room_price) {
		super();
		this.room_id = room_id;
		this.room_number = room_number;
		this.room_status = room_status;
		this.room_price = room_price;
	}

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public int getRoom_number() {
		return room_number;
	}

	public void setRoom_number(int room_number) {
		this.room_number = room_number;
	}

	public String getRoom_status() {
		return room_status;
	}

	public void setRoom_status(String room_status) {
		this.room_status = room_status;
	}

	public long getRoom_price() {
		return room_price;
	}

	public void setRoom_price(long room_price) {
		this.room_price = room_price;
	}
	
}
