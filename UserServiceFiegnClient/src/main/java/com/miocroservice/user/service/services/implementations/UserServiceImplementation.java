package com.miocroservice.user.service.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miocroservice.user.service.entities.Hotel;
import com.miocroservice.user.service.entities.Rating;
import com.miocroservice.user.service.entities.User;
import com.miocroservice.user.service.exceptions.ResourceNotFoundException;
import com.miocroservice.user.service.repositoriees.UserRepository;
import com.miocroservice.user.service.services.UserService;
import com.miocroservice.user.service.services.external.HotelService;
import com.miocroservice.user.service.services.external.RatingService;

@Service
public class UserServiceImplementation implements UserService {


	@Autowired
	private UserRepository userRepository;

	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private RatingService ratingService;

	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImplementation.class);

	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {


		ArrayList<User> userList = (ArrayList<User>) userRepository.findAll();

		for (User user : userList) {

			List<Rating> ratings = ratingService.getRatingByUserId(user.getUserId());

			for (Rating rating : ratings) {
				Hotel hotel = hotelService.getHotelByHotelId(rating.getHotelId());
				rating.setHotel(hotel);
			}
   
			user.setRatings(ratings);
            LOGGER.info("Get All User Completed!");
		}

		return userList;
	}

	@Override
	public User getUser(String userId) {

		// Getting user from database using userId
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User with given id is not found on server : " + userId));

		// Fetching rating for the user by userId
		List<Rating> ratings = ratingService.getRatingByUserId(user.getUserId());

		for (Rating rating : ratings) {
			Hotel hotel = hotelService.getHotelByHotelId(rating.getHotelId());
			rating.setHotel(hotel);
		}

		user.setRatings(ratings);

		return user;
	}

}
