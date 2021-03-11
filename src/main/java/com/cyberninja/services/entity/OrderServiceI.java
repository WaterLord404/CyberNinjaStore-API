package com.cyberninja.services.entity;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.cyberninja.model.entity.dto.OrderDTO;
import com.cyberninja.model.entity.dto.OrderDetailsDTO;

public interface OrderServiceI {

	public List<OrderDetailsDTO> getProductCart(List<OrderDetailsDTO> ids);

	public OrderDTO purchaseOrder(List<OrderDetailsDTO> dtos, Authentication auth, String couponCode);
	
}
