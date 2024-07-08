package com.miocroservice.hotel.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miocroservice.hotel.service.entities.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String>{

}
