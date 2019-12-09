package com.hotelsapi.service;


import java.util.List;

import com.hotelsapi.entity.User;

public interface UserService {

	User createUser(User user);

	List<User> getUsers();

}
