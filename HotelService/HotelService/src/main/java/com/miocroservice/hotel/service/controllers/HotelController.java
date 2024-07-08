package com.miocroservice.hotel.service.controllers;

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

import com.miocroservice.hotel.service.entities.Hotel;
import com.miocroservice.hotel.service.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@PostMapping("/createHotel")
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
		Hotel createdHotel = hotelService.createHotel(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdHotel);
	}
	
	@GetMapping("/getHotel/{hotelId}")
	public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId){
		Hotel hotel = hotelService.getHotelById(hotelId);
		return ResponseEntity.ok(hotel);
	}
	
	@GetMapping("/getAllHotels")
	public ResponseEntity<List<Hotel>> getAllHotel(){
		List<Hotel> hotelList = hotelService.getAllHotels();
		return ResponseEntity.ok(hotelList);
	}
	
	

}
