package com.ecom.project.exception;

public class BadUserCredentialException extends RuntimeException {
	public BadUserCredentialException() {
		super(" Invalid UserNAme or Password ");
	}

	public BadUserCredentialException(String message) {
		super(message);
	}
}
