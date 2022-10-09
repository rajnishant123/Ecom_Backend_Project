package com.ecom.project.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException() {
		super(" resource not Found on Server");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
