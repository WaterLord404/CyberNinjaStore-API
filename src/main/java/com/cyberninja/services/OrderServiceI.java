package com.cyberninja.services;

import org.springframework.security.core.Authentication;

import com.cyberninja.model.entity.dto.OrderDTO;

public interface OrderServiceI {

	public OrderDTO purchaseOrder(OrderDTO dto, Authentication auth);
	
}
