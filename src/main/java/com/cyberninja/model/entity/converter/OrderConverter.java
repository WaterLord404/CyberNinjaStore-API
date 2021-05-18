package com.cyberninja.model.entity.converter;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<OrderDTO> ordersToOrdersDTO(List<Order> orders) {
		List<OrderDTO> dtos = new ArrayList<>();
		
		for (Order order : orders) {
			dtos.add(orderToOrderDTO(order));
		}
		
		return dtos;
	}
	
}
