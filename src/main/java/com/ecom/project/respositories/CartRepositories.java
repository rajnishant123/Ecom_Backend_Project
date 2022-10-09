package com.ecom.project.respositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.project.models.Cart;
import com.ecom.project.models.User;

public interface CartRepositories extends JpaRepository<Cart,Integer>{
	
	Optional<Cart> findByUser(User user);

}
