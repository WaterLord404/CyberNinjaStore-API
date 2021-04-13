package com.cyberninja.services.entity;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.cyberninja.model.entity.Cart;
import com.cyberninja.model.entity.Customer;
import com.cyberninja.model.entity.dto.OrderDetailsDTO;

public interface CartServiceI {
	
	List<OrderDetailsDTO> getCartProducts(Authentication auth);
	
	List<OrderDetailsDTO> saveCart(List<OrderDetailsDTO> dtos, Authentication auth);
	
	List<OrderDetailsDTO> getProductCart(List<OrderDetailsDTO> dtos);
	
	void createCart(Customer customer);
	
	Cart getCart(Authentication auth);
}
