package com.ecom.project.respositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.ecom.project.models.User;


public interface UserRespositories extends JpaRepository<User,Integer> {
	
	//Custom Finder Method
	
	//username is email for our project
	public Optional <User> findByEmail(String email);
	public List<User> findByName(String name);
	public List<User> findByActiveTrue();
	public List<User> findByNameAndAddress(String name,String Address);
	public List<User> findByAboutIsNull();
	public List<User>findByNameStartingWith(String prefix);
	public List<User>findByNameContaining(String infix);
	
	public List<User> findByNameOrderByNameAsc(String Name);
	
	public List<User> findTop5ByUserId(int userId);
	
	//creating complex queries
	
	
	@Query("select u from User u where u.userId= :userId and u.email= :email")
	public List<User> getUserByEmail(@Param("userId") int abcId, String email);
}
