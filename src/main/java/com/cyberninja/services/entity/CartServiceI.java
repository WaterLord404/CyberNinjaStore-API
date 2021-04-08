package com.cyberninja.services.entity;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.cyberninja.model.entity.Customer;
import com.cyberninja.model.entity.dto.OrderDetailsDTO;

public interface CartServiceI {
	
	List<OrderDetailsDTO> getCart(Authentication auth);
	
	List<OrderDetailsDTO> saveCart(Authentication auth);
	
	List<OrderDetailsDTO> getProductCart(List<OrderDetailsDTO> dtos);
	
	void createCart(Customer customer);
}
