package com.ecom.project.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;

import com.ecom.project.exception.BadUserCredentialException;
import com.ecom.project.payload.JwtRequest;
import com.ecom.project.payload.JwtResponse;
import com.ecom.project.payload.UserDto;
import com.ecom.project.security.JwtHelper;

@RequestMapping("/auth")
@RestController
public class AuthController {
			@Autowired
	    private AuthenticationManager manager;

	    @Autowired
	    private UserDetailsService userDetailsService;

	    @Autowired
	    private JwtHelper helper;
	    @Autowired
	    private ModelMapper mapper;
	    @PostMapping("/login")
	    public ResponseEntity<JwtResponse> login(
	            @RequestBody JwtRequest request
	    ) throws Exception {

	        //authenticate
	        this.authenticateUser(request.getUsername(), request.getPassword());
	        ///age ka kam
	        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

	        String token = this.helper.generateToken(userDetails);

	        JwtResponse response = new JwtResponse();
	        response.setToken(token);
	        response.setUser(this.mapper.map(userDetails, UserDto.class));
	        return new ResponseEntity<>(response, HttpStatus.OK);


	    }


	    private void authenticateUser(String username, String password) throws Exception {


	        try {
	            //authenticate
	            manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	        } catch (BadCredentialsException e) {

	            throw new BadUserCredentialException("Invalid username or Password !!");

	        } catch (DisabledException e) {
	            throw new BadUserCredentialException("User is not active !!");
	        }


	    }
}
