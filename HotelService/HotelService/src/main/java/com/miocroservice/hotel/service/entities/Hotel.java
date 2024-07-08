package com.miocroservice.hotel.service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="HOTELS")
public class Hotel {
	
	@Id
	@Column(name="HOTEL_ID")
	private String hotelId;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="LOCATION")
	private String location;
	
	@Column(name="ABOUT")
	private String about;

}