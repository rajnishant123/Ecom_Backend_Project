package com.ecom.project.payload;


public class ApiRespons {

	private String message;
	private boolean success;
	
	public String getMessage() {
		return message;
	}
	public ApiRespons(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
