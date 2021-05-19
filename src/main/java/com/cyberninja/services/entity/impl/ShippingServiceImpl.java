package com.cyberninja.services.entity.impl;

import static com.cyberninja.model.entity.enumerated.ShippingStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.model.entity.Order;
import com.cyberninja.model.entity.Shipping;
import com.cyberninja.model.entity.converter.ShippingConverter;
import com.cyberninja.model.entity.dto.ShippingDTO;
import com.cyberninja.model.repository.ShippingRepository;
import com.cyberninja.security.services.UserServiceI;
import com.cyberninja.services.entity.ShippingServiceI;

@Service
public class ShippingServiceImpl implements ShippingServiceI {

	@Autowired private ShippingRepository shippingRepo;
	
	@Autowired private ShippingConverter shippingConverter;
	
	@Autowired private UserServiceI userService;
	
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
	public ShippingDTO updateShipping(Authentication auth, Long shippingId, ShippingDTO dto) throws ParseException {
		Shipping shipping = shippingRepo.findById(shippingId)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
		
		shipping.setUser(userService.getUserById(Long.parseLong(auth.getName())));
		shipping.setUpdateDate(new Date());
		shipping.setCounty(dto.getCounty());
		shipping.setState(dto.getState());
		shipping.setVillage(dto.getVillage());
		shipping.setStatus(dto.getStatus());

		shippingRepo.save(shipping);

		return shippingConverter.shippingToShippingDTO(shipping);
	}

	/**
	 * Actualiza todos los envios que estan en 
	 */
	@Override
	public List<ShippingDTO> updateShippings(Authentication auth, ShippingDTO dto) {
		List<Shipping> shippings = shippingRepo.findShippingsInTransitForShipper(Long.parseLong(auth.getName()));
		
		if (shippings.isEmpty()) {
			throw new ResponseStatusException(NOT_FOUND);
		}
		
		for (Shipping shipping : shippings) {
			shipping.setUpdateDate(new Date());
			shipping.setCounty(dto.getCounty());
			shipping.setState(dto.getState());
			shipping.setVillage(dto.getVillage());
			shipping.setStatus(dto.getStatus());

			shippingRepo.save(shipping);
		}
		
		return shippingConverter.shippingsToShippingsDTO(shippings);
	}

}
