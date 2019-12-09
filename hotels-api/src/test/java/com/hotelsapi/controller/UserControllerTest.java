package com.hotelsapi.controller;


import java.net.URI;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelsapi.controller.UserController;
import com.hotelsapi.entity.User;
import com.hotelsapi.repository.UserRepository;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@InjectMocks
	UserController userController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	UserRepository userRepository;
		
	String exampleUserJson = "{\n" + 
			"	\"user_name\":\"Kunal\",\n" + 
			"	\"bonus_points\":1600\n" + 
			"}";
	
	@Test
	public void testCreateUser() throws Exception{
		RestTemplate restTemplate = new RestTemplate();
	    final String baseUrl = "http://localhost:"+"8080"+"/api/v1/hotel-api/users";
	    URI uri = new URI(baseUrl);
	    User employee = new User(0, "Kunal", 1600L);
	     
	    HttpHeaders headers = new HttpHeaders();
	 
	    HttpEntity<User> request = new HttpEntity<>(employee, headers);
	     
	    try
	    {
	    	ResponseEntity<Object> result = restTemplate.postForEntity(uri, request, Object.class);
	        Assert.assertEquals(201, result.getStatusCodeValue());
	    }
	    catch(HttpClientErrorException ex) 
	    {
	        Assert.assertEquals(400, ex.getRawStatusCode());
	        Assert.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
	    }
	}
	
}
