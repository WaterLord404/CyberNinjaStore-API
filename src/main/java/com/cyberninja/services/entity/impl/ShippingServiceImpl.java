package com.cyberninja.services.entity.impl;

import static com.cyberninja.model.entity.enumerated.ShippingStatus.ACCEPTED;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyberninja.model.entity.Order;
import com.cyberninja.model.entity.Shipping;
import com.cyberninja.model.repository.ShippingRepository;
import com.cyberninja.services.entity.ShippingServiceI;

@Service
public class ShippingServiceImpl implements ShippingServiceI {

	@Autowired private ShippingRepository shippingRepo;
	
	/**
	 * Añade un envio
	 */
	@Override
	public void addShipping(Order order) {
		Shipping shipping = new Shipping();
		
		shipping.setOrder(order);
		shipping.setStatus(ACCEPTED);
		shipping.setUpdateDate(LocalDateTime.now());
		
		order.setShipping(shipping);
	
		shippingRepo.save(shipping);
	}

}
