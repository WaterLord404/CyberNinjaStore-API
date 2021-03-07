package com.cyberninja.services;

import java.util.List;

import com.cyberninja.model.entity.dto.OrderDetailsDTO;
import com.cyberninja.model.entity.dto.ProductDTO;

public interface OrderServiceI {

	public List<OrderDetailsDTO> getProductCart(List<OrderDetailsDTO> ids);

//	public OrderDTO purchaseOrder(OrderDTO dto, Authentication auth);
	
}
