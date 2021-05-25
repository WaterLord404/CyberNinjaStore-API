package com.cyberninja.services.entity;

import java.text.ParseException;
import java.util.List;

import org.springframework.security.core.Authentication;

import com.cyberninja.model.entity.Order;
import com.cyberninja.model.entity.dto.ShippingDTO;

public interface ShippingServiceI {
	
	void addShipping(Order order) throws ParseException;

	ShippingDTO updateShipping(Authentication auth, String uuid, ShippingDTO dto, Boolean newShipping) throws ParseException;

	List<ShippingDTO> syncShippings(Authentication auth, ShippingDTO dto);

	List<ShippingDTO> getShippings(Authentication auth);
}
