package com.miocroservice.user.service.services.implementations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.miocroservice.user.service.entities.Hotel;
import com.miocroservice.user.service.entities.Rating;
import com.miocroservice.user.service.entities.User;
import com.miocroservice.user.service.exceptions.ResourceNotFoundException;
import com.miocroservice.user.service.repositoriees.UserRepository;
import com.miocroservice.user.service.services.UserService;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoadBalancerClient loadBalancerClient;

	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImplementation.class);

	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {

		ServiceInstance ratingServiceInstance = loadBalancerClient.choose("RATING-SERVICE");
		ServiceInstance hotelServiceInstance = loadBalancerClient.choose("HOTEL-SERVICE");

		ArrayList<User> userList = (ArrayList<User>) userRepository.findAll();

		for (User user : userList) {

			// Fetching rating for the user by userId
			Rating[] ratingsOfUser = restTemplate.getForObject("http://" + ratingServiceInstance.getHost() + ":"
					+ ratingServiceInstance.getPort() + "/ratings/getRatingsByUserId/" + user.getUserId(),
					Rating[].class);

			List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

			for (Rating rating : ratings) {
				ResponseEntity<Hotel> hotelEntity = restTemplate.getForEntity("http://" + hotelServiceInstance.getHost()
						+ ":" + hotelServiceInstance.getPort() + "/hotels/getHotel/" + rating.getHotelId(),
						Hotel.class);
				rating.setHotel(hotelEntity.getBody());
			}
   
			user.setRatings(ratings);
            LOGGER.info("Get All User Completed!");
		}

		return userList;
	}

	@Override
	public User getUser(String userId) {

		ServiceInstance ratingServiceInstance = loadBalancerClient.choose("RATING-SERVICE");
		ServiceInstance hotelServiceInstance = loadBalancerClient.choose("HOTEL-SERVICE");

		// Getting user from database using userId
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User with given id is not found on server : " + userId));

		// Fetching rating for the user by userId
		Rating[] ratingsOfUser = restTemplate.getForObject("http://" + ratingServiceInstance.getHost() + ":"
				+ ratingServiceInstance.getPort() + "/ratings/getRatingsByUserId/" + user.getUserId(), Rating[].class);

		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

		for (Rating rating : ratings) {
			ResponseEntity<Hotel> hotelEntity = restTemplate.getForEntity("http://" + hotelServiceInstance.getHost()
					+ ":" + hotelServiceInstance.getPort() + "/hotels/getHotel/" + rating.getHotelId(), Hotel.class);
			rating.setHotel(hotelEntity.getBody());
		}

		user.setRatings(ratings);

		return user;
	}

}
