package com.ecom.project.service;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.project.exception.ResourceNotFoundException;
import com.ecom.project.models.Cart;
import com.ecom.project.models.CartItem;
import com.ecom.project.models.Product;
import com.ecom.project.models.User;
import com.ecom.project.payload.CartDto;
import com.ecom.project.payload.ItemRequest;
import com.ecom.project.respositories.CartRepositories;
import com.ecom.project.respositories.ProductRepositories;
import com.ecom.project.respositories.UserRespositories;


@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepositories cartRepo;
	@Autowired
	private UserRespositories userRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	
	@Autowired
	private ProductRepositories productRepo;
	
	
	@Override
	public CartDto addItem(ItemRequest item, String userName) {
		int productId = item.getProductId();
		int quantity = item.getQuantity();

		if (quantity <= 0) {
			throw new ResourceNotFoundException("Invalid Quantity !!");			
		}

		// get the user
		User user = this.userRepo.findByEmail(userName).orElseThrow(() -> new ResourceNotFoundException());

		// get the product from db: productRepository
		Product product = this.productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found !!"));
		if (!product.isStock()) {
			throw new ResourceNotFoundException("Product is out of stock");
		}
		//System.out.println(product.getProductPrice());

		// create new cartItem: with product and quantity

		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		cartItem.setQuantity(quantity);
		cartItem.setTotalProductPrice();

		// getting cart from user
		Cart cart = user.getCart();

		// if cart is null that means user does not have any cart
		if (cart == null) {
			// we will create new cart
			cart = new Cart();
			cart.setUser(user);

		}

		// add items mein cart ko

		Set<CartItem> items = cart.getItems();

		AtomicReference<Boolean> flag = new AtomicReference<>(false);

		Set<CartItem> newItems = items.stream().map((i) -> {
			// changes
			if (i.getProduct().getProductId() == product.getProductId()) {

				i.setQuantity(quantity);
				i.setTotalProductPrice();
				flag.set(true);
			}
			return i;
		}).collect(Collectors.toSet());

		// check
		if (flag.get()) {
			// newItems ko items ki jagah set karenge
			items.clear();
			items.addAll(newItems);
		} 
		else {
			cartItem.setCart(cart);
			items.add(cartItem);
		}

		Cart updatedCart = this.cartRepo.save(cart);

		return this.mapper.map(updatedCart, CartDto.class);
	}

	@Override
	public CartDto get(String userName) {
		User user = this.userRepo.findByEmail(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with given username !!"));

		Cart cart = this.cartRepo.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Cart not found !!"));

		return this.mapper.map(cart, CartDto.class);
	}

	@Override
	public CartDto removeItem(String username, int productId) {
		User user = this.userRepo.findByEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with given username !!"));

		Cart cart = user.getCart();

		Set<CartItem> items = cart.getItems();

		boolean result = items.removeIf((item) -> item.getProduct().getProductId() == productId);
		System.out.println(result);

		Cart updatedCart = cartRepo.save(cart);

		return this.mapper.map(updatedCart, CartDto.class);
	}


}
