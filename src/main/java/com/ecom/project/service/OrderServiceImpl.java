package com.ecom.project.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.project.exception.ResourceNotFoundException;
import com.ecom.project.models.Cart;
import com.ecom.project.models.CartItem;
import com.ecom.project.models.Order;
import com.ecom.project.models.OrderItem;
import com.ecom.project.models.User;
import com.ecom.project.payload.OrderDto;
import com.ecom.project.payload.OrderRequest;
import com.ecom.project.respositories.CartRepositories;
import com.ecom.project.respositories.OrderRepository;
import com.ecom.project.respositories.UserRespositories;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRespositories userRepository;

	@Autowired
	private CartRepositories cartRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public OrderDto createOrder(OrderRequest request, String username) {

		User user = this.userRepository.findByEmail(username).orElseThrow(ResourceNotFoundException::new);
		int cartId = request.getCartId();
		String address = request.getAddress();

		Cart cart = this.cartRepository.findById(cartId).orElseThrow(ResourceNotFoundException::new);

		Set<CartItem> items = cart.getItems();

		Order order = new Order();
		AtomicReference<Double> totalOrderPrice = new AtomicReference<>(0.0);
		Set<OrderItem> orderItems = items.stream().map((cartItem) -> {

			OrderItem orderItem = new OrderItem();
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setTotalPrice(cartItem.getTotalProductPrice());
			orderItem.setOrder(order);
			totalOrderPrice.set(totalOrderPrice.get() + orderItem.getTotalPrice());

			//
			int productId = orderItem.getProduct().getProductId();
			// product:- fetch

			// update the product quantity

			// save the product

			return orderItem;

		}).collect(Collectors.toSet());

		order.setItems(orderItems);
		order.setBillingAddress(address);
		order.setPaymentStatus("NOT PAID");
		order.setOrderAmount(totalOrderPrice.get());
		order.setOrderCretaedAt(new Date());
		order.setOrderDeliveredDate(null);
		order.setOrderStatus("CREATED");
		order.setUser(user);

		Order savedOrder = this.orderRepository.save(order);

		cart.getItems().clear();

		this.cartRepository.save(cart);

		return this.mapper.map(savedOrder, OrderDto.class);
	}

	@Override
	public OrderDto updateOrder(OrderDto orderDto, int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrder(int orderId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<OrderDto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDto get(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}
}
