package com.ecom.project.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.project.models.Order;
import com.ecom.project.models.User;

public interface OrderRepository extends JpaRepository<Order,Integer>{
	List<Order> findByUser(User user);
}
