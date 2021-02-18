package com.techshop.services;

import org.springframework.security.core.Authentication;

import com.techshop.model.entity.dto.OrderDTO;

public interface OrderServiceI {

	public OrderDTO purchaseOrder(OrderDTO dto, Authentication auth);
	
}
