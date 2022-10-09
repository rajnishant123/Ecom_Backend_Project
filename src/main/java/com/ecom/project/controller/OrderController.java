package com.ecom.project.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.project.payload.OrderDto;
import com.ecom.project.payload.OrderRequest;
import com.ecom.project.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	   @Autowired
	    private OrderService orderService;
	   
	   

	    @PostMapping("/")
	    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderRequest request,Principal principal) {

	        OrderDto createOrder = this.orderService.createOrder(request,principal.getName());

	        return new ResponseEntity<OrderDto>(createOrder, HttpStatus.CREATED);
	    }
}
