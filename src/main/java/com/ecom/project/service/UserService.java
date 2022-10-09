package com.ecom.project.service;

import java.util.List;

import com.ecom.project.payload.UserDto;

public interface UserService {
	
	//create
	
	UserDto create(UserDto userDto);
	
	//update
	
	UserDto update (UserDto userDto, int userId);
	
	// delete
	
	void delete(int userId);
	
	//get single User
	
	UserDto getByUserId(int userId);
	
	//get All Users
	
	List<UserDto> getAllUsers();
	
	//getByemail
	
	UserDto getByEmail(String email);
	

}
