package com.cyberninja.services.entity;

import java.text.ParseException;
import java.util.List;

import org.springframework.security.core.Authentication;

import com.cyberninja.model.entity.Order;
import com.cyberninja.model.entity.dto.ShippingDTO;

public interface ShippingServiceI {
	
	void addShipping(Order order) throws ParseException;

	ShippingDTO updateShipping(Authentication auth, Long shippingId, ShippingDTO dto) throws ParseException;

	List<ShippingDTO> updateShippings(Authentication auth, ShippingDTO dto);
}
