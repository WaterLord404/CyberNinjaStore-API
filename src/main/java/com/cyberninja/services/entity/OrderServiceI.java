package com.cyberninja.services.entity;

import java.text.ParseException;
import java.util.List;

import org.springframework.security.core.Authentication;

import com.cyberninja.model.entity.Order;
import com.cyberninja.model.entity.dto.OrderDTO;
import com.cyberninja.model.entity.dto.OrderDetailsDTO;

public interface OrderServiceI {

	OrderDTO purchaseOrder(List<OrderDetailsDTO> dtos, Authentication auth, String couponCode) throws ParseException;

	Order createOrder(Authentication auth);

	Order getOrderOrCreate(Authentication auth);

	List<OrderDTO> getOrdersWithShipping(Authentication auth);

}
