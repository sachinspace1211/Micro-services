package com.miocroservice.user.service.services.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.miocroservice.user.service.entities.Hotel;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelService {
	
	@GetMapping("/hotels/getHotel/{hotelId}")
	public Hotel getHotelByHotelId(@PathVariable String hotelId);

}
