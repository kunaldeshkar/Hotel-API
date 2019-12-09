package com.hotelsapi.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.hotelsapi.controller.UserController;
import com.hotelsapi.entity.User;
import com.hotelsapi.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class ControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	String exampleUserJson = "{\n" + 
			"	\"user_name\":\"Kunal\",\n" + 
			"	\"bonus_points\":1500\n" + 
			"}";
	
	User mockUser = new User(1, "Kunal", 1600);
	List<User> user = new ArrayList<>();
	
	
	@Test
	public void verifyCreateUser() throws Exception{
		
		User user = new User(1,"Kunal",1500);
		
		Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(user);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("http://localhost:8080/api/v1/hotel-api/users")
				.content(exampleUserJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost:8080/api/v1/hotel-api/users/0",
				response.getHeader(HttpHeaders.LOCATION));
		
	}
	
	@Test
	public void verifyGetUsers() throws Exception{
		
		user.add(mockUser);
		
		Mockito.when(
				userService.getUsers()).thenReturn(user);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"http://localhost:8080/api/v1/hotel-api/users").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "[{user_id:1,user_name:Kunal,bonus_points:1600,room:[]}]";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
}
