package com.miocroservice.hotel.service.exception.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.miocroservice.hotel.service.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handlerResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
		Map<String, Object> map = new HashMap<>();
		map.put("message",resourceNotFoundException.getMessage());
		map.put("success", false);
		map.put("status", HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
	}

}
