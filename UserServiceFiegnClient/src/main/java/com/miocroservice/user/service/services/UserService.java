package com.miocroservice.user.service.services;

import java.util.List;

import com.miocroservice.user.service.entities.User;

public interface UserService {
	
	User saveUser(User user);
	
	List<User> getAllUser();
	
	User getUser(String userId);

}
