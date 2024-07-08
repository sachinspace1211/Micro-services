package com.miocroservice.user.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miocroservice.user.service.entities.User;
import com.miocroservice.user.service.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/createUser")
	public ResponseEntity<User> createUser(@RequestBody User user){
		User createdUser = userService .saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	@GetMapping("/getUser/{userId}")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId){
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUsers = userService.getAllUser();
		return ResponseEntity.ok(allUsers);
	}

}
