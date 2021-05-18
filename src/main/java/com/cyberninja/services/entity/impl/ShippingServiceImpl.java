package com.cyberninja.services.entity.impl;

import static com.cyberninja.model.entity.enumerated.ShippingStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.model.entity.Order;
import com.cyberninja.model.entity.Shipping;
import com.cyberninja.model.entity.converter.ShippingConverter;
import com.cyberninja.model.entity.dto.ShippingDTO;
import com.cyberninja.model.repository.ShippingRepository;
import com.cyberninja.services.entity.ShippingServiceI;

@Service
public class ShippingServiceImpl implements ShippingServiceI {

	@Autowired private ShippingRepository shippingRepo;
	
	@Autowired private ShippingConverter shippingConverter;
	
	
	/**
	 * Añade un envio
	 * @throws ParseException 
	 */
	@Override
	public void addShipping(Order order) throws ParseException {
		Shipping shipping = new Shipping();
		shipping.setOrder(order);
		shipping.setStatus(ACCEPTED);
		shipping.setUpdateDate(new Date());
		
		order.setShipping(shipping);
	
		shippingRepo.save(shipping);
	}


	/**
	 * Actualiza un envio
	 * @throws ParseException 
	 */
	@Override
	public ShippingDTO updateShipping(Long shippingId, ShippingDTO dto) throws ParseException {
		Shipping shipping = shippingRepo.findById(shippingId)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
		
		shipping.setUpdateDate(new Date());
		shipping.setCounty(dto.getCounty());
		shipping.setState(dto.getState());
		shipping.setVillage(dto.getVillage());
		shipping.setStatus(dto.getStatus());

		shippingRepo.save(shipping);

		return shippingConverter.shippingToShippingDTO(shipping);
	}

}
