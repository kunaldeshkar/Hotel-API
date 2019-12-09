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
import com.hotelsapi.entity.User;
import com.hotelsapi.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	public void testCreateUser(){

		User user = new User(1, "Kunal", 1500);
		
	    Mockito.when(userRepository.save(user)).thenReturn(user);
	    
	    assertThat(userService.createUser(user)).isEqualTo(user);
	
	}
	
	@Test
	public void testGetUsers() {
		List<Room> rooms = new ArrayList<Room>();
    	
    	User user = new User();
    	user.setUser_id(1);
    	user.setUser_name("Kunal");
    	user.setBonus_points(1500);
    	user.setRoom(rooms);
    	
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(user);
        
        Mockito.when(userRepository.findAll()).thenReturn(expectedUsers);
	    
	    assertThat(userService.getUsers()).isEqualTo(expectedUsers);
	}
}
