package com.cyberninja.model.entity.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cyberninja.model.entity.OrderDetails;
import com.cyberninja.model.entity.dto.OrderDetailsDTO;

@Component
public class OrderDetailsConverter {

	public OrderDetails orderDetailsDTOToOrderDetails(OrderDetailsDTO dto) {
		OrderDetails orderDetails = new OrderDetails();
		
		orderDetails.setUnits(dto.getUnits());
		orderDetails.setColor(dto.getColor());
		orderDetails.setSize(dto.getSize());

		return orderDetails;
	}
	
	public List<OrderDetails> orderDetailsDTOToOrderDetails(List<OrderDetailsDTO> dtos) {
		List<OrderDetails> ordersDetails = new ArrayList<>();
		for (OrderDetailsDTO orderDetailsDTO : dtos) {
			ordersDetails.add(orderDetailsDTOToOrderDetails(orderDetailsDTO));
		}
		return ordersDetails;
	}
}
