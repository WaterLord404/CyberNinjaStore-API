package com.cyberninja.model.entity.converter;

import org.springframework.stereotype.Component;

import com.cyberninja.model.entity.Order;
import com.cyberninja.model.entity.dto.OrderDTO;

@Component
public class OrderConverter {

	public OrderDTO orderToOrderDTO(Order order) {
		OrderDTO dto = new OrderDTO();
		
		dto.setId(order.getId());
		dto.setTotalPrice(order.getTotalPrice());
		
		return dto;
	}
	
}
