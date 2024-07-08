
package com.miocroservice.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	

	private static final long serialVersionUID = 3942946950603026719L;
	
	public ResourceNotFoundException() {
		super("Resource not found on server!!");
	}
    public ResourceNotFoundException(String message) {
    	super(message);
    }
}
