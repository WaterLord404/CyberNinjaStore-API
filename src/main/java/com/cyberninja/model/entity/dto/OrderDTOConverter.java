package com.cyberninja.model.entity.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.cyberninja.model.entity.Order;

@Component
public class OrderDTOConverter {

	public Order orderDTOToOrder(OrderDTO dto) {
		Order order = new Order();
		
		order.setPurchaseTime(LocalDate.now());
		
		return order;
	}
	
	public OrderDTO orderToOrderDTO(Order order) {
		OrderDTO dto = new OrderDTO();
		
		dto.setId(order.getId());
		dto.setTotalPrice(order.getTotalPrice());
		
		return dto;
	}
	
}
