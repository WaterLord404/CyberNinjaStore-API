package com.cyberninja.model.entity.converter;

import org.springframework.stereotype.Component;

import com.cyberninja.model.entity.Shipping;
import com.cyberninja.model.entity.dto.ShippingDTO;

@Component
public class ShippingConverter {
	
	public ShippingDTO shippingToShippingDTO(Shipping shipping) {
		ShippingDTO dto = new ShippingDTO();

		dto.setUpdateDate(shipping.getUpdateDate());
		dto.setCounty(shipping.getCounty());
		dto.setState(shipping.getState());
		dto.setVillage(shipping.getVillage());
		dto.setStatus(shipping.getStatus());

		return dto;
	}

}
