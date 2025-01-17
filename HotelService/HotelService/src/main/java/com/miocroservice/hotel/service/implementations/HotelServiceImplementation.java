package com.miocroservice.hotel.service.implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miocroservice.hotel.service.entities.Hotel;
import com.miocroservice.hotel.service.exceptions.ResourceNotFoundException;
import com.miocroservice.hotel.service.repositories.HotelRepository;
import com.miocroservice.hotel.service.services.HotelService;

@Service
public class HotelServiceImplementation implements HotelService{
	
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel createHotel(Hotel hotel) {
		String hotelId = UUID.randomUUID().toString();
		hotel.setHotelId(hotelId);
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotels() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotelById(String hotelId) {
		return hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel with given id not found"));
	}

}
