package com.cyberninja.services.entity;

import java.text.ParseException;

import com.cyberninja.model.entity.Order;
import com.cyberninja.model.entity.dto.ShippingDTO;

public interface ShippingServiceI {
	
	void addShipping(Order order) throws ParseException;

	ShippingDTO updateShipping(Long shippingId, ShippingDTO dto) throws ParseException;
}
