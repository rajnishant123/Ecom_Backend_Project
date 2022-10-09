package com.ecom.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecom.project.exception.ResourceNotFoundException;
import com.ecom.project.models.User;
import com.ecom.project.respositories.UserRespositories;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	UserRespositories userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loading user>>>>>>>>>");
		 User user = this.userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User not found with given username"));
	        return user;
	}

}
