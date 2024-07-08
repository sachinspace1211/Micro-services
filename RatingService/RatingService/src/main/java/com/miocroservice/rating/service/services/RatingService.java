package com.miocroservice.rating.service.services;

import java.util.List;

import com.miocroservice.rating.service.entities.Rating;

public interface RatingService {
	
	public Rating createRating(Rating rating);
	
	public List<Rating> getAllRatings();
	
	public List<Rating> getRatingsByUserId(String userId);
	
	public List<Rating> getRatingsByHotelId(String hotelId);

}
