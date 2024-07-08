package com.miocroservice.hotel.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 8267396535163034037L;
	
	public ResourceNotFoundException() {
		super("Hotel not found");
	}
	public ResourceNotFoundException(String message){
		super(message);
	}

}
