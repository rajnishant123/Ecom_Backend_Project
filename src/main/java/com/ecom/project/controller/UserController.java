package com.ecom.project.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.project.payload.ApiRespons;
import com.ecom.project.payload.UserDto;
import com.ecom.project.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// create User
	@PostMapping("/")
	public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto) {

		userDto.setActive(true);
		userDto.setCreateAt(new Date());
		userDto.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
		UserDto cretaeduser = this.userService.create(userDto);
		return new ResponseEntity<UserDto>(cretaeduser, HttpStatus.CREATED);
	}

	// update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> update(@Valid @RequestBody UserDto userDto, @PathVariable int userId) {

		UserDto updatedUser = this.userService.update(userDto, userId);
		return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);

	}

	// delete user
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiRespons> delete(@PathVariable int userId) {

		this.userService.delete(userId);

		return new ResponseEntity<ApiRespons>(new ApiRespons("User Deleted Successfully", true), HttpStatus.OK);

	}

	// get all user
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser() {
		List<UserDto> all = this.userService.getAllUsers();
		return new ResponseEntity<List<UserDto>>(all, HttpStatus.OK);
	}

	// get single user
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable int userId) {
		UserDto user = this.userService.getByUserId(userId);
		return new ResponseEntity<UserDto>(user, HttpStatus.FOUND);
	}

	// get User By email

	@GetMapping("/email/{email}")
	public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
		UserDto user = this.userService.getByEmail(email);
		return new ResponseEntity<UserDto>(user, HttpStatus.FOUND);
	}

}
