package com.hotelsapi.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.hotelsapi.entity.Room;
import com.hotelsapi.entity.User;
import com.hotelsapi.repository.UserRepository;

@RunWith(SpringRunner.class)
public class UserServiceTest {

	@Mock
    private UserRepository userRespository;

    @InjectMocks
    private UserService userService;
    
    
   // @Test
    public void verifyGetUsers() {
    	//given
    	List<Room> rooms = new ArrayList<Room>();
    	
    	User user = new User();
    	user.setUser_id(1);
    	user.setUser_name("Kunal");
    	user.setBonus_points(1500);
    	user.setRoom(rooms);
    	
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(user);
        
        when(userService.getUsers()).thenReturn(expectedUsers);
        List<User> userList = userService.getUsers();
        assertEquals(1, userList.size());
        verify(userService, times(1)).getUsers();
    }
    
    @Test
    public void createUserTest()
    {
        User user = new User(1,"Kunal",1500);
        userService.createUser(user);
        verify(userService, times(1)).createUser(user);
    }
}
