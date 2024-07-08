package com.miocroservice.rating.service.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.miocroservice.rating.service.entities.Rating;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String>{
	
	//Custom Finder Method
	List<Rating> findByUserId(String userId);
	List<Rating> findByHotelId(String hotelId);

}
