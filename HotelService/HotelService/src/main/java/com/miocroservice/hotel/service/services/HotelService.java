package com.miocroservice.hotel.service.services;

import java.util.List;

import com.miocroservice.hotel.service.entities.Hotel;

public interface HotelService {
	
	Hotel createHotel(Hotel hotel);
	
	List<Hotel> getAllHotels();
	
	Hotel getHotelById(String hotelId);

}
