package com.miocroservice.rating.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miocroservice.rating.service.entities.Rating;
import com.miocroservice.rating.service.services.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/createRating")
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
		Rating createdRating = ratingService.createRating(rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRating);
	}
	
	@GetMapping("/getAllRatings")
	public ResponseEntity<List<Rating>> getAllRatings(){
		List<Rating> ratingList = ratingService.getAllRatings();
		return ResponseEntity.status(HttpStatus.OK).body(ratingList);
	}
	
	@GetMapping("/getRatingsByUserId/{userId}")
	public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId){
		List<Rating> ratingList = ratingService.getRatingsByUserId(userId);
		return ResponseEntity.status(HttpStatus.OK).body(ratingList);
	}
	
	@GetMapping("/getRatingsByHotelId/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingsByhotelId(@PathVariable String hotelId){
		List<Rating> ratingList = ratingService.getRatingsByHotelId(hotelId);
		return ResponseEntity.status(HttpStatus.OK).body(ratingList);
	}

}
