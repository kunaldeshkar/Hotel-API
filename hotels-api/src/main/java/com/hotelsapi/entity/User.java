package com.hotelsapi.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	private String user_name;
	private long bonus_points;
	
	@OneToMany(cascade = CascadeType.ALL)
    private List<Room> room = new ArrayList<Room>();
	
	public User() {
		
	}

	public User(int user_id, String user_name, long bonus_points) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.bonus_points = bonus_points;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public long getBonus_points() {
		return bonus_points;
	}

	public void setBonus_points(long bonus_points) {
		this.bonus_points = bonus_points;
	}

	public List<Room> getRoom() {
		return room;
	}

	public void setRoom(List<Room> room) {
		this.room = room;
	}
	
}
