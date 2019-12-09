package com.hotelsapi.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hotelsapi.entity.User;
import com.hotelsapi.service.UserService;
import com.hotelsapi.util.PathProxy;

@RestController
@RequestMapping(value = { PathProxy.HOTEL_API })
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user){
		 
		userService.createUser(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(user.getUser_id()).toUri();
		
		return ResponseEntity.created(location).build(); 
	}
	
	@GetMapping("/users")
	public ResponseEntity<Object> getUsers(){
		List<User>  users = userService.getUsers();
		
		return ResponseEntity.ok(users);
	}
}
