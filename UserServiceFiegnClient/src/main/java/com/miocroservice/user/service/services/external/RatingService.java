package com.miocroservice.user.service.services.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.miocroservice.user.service.entities.Rating;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {
	
	@GetMapping("/ratings/getRatingsByUserId/{userId}")
	public List<Rating> getRatingByUserId(@PathVariable("userId") String userId);

}
