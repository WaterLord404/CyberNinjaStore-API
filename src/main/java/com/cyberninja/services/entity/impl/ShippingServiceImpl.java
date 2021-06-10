package com.cyberninja.services.entity.impl;

import static com.cyberninja.model.entity.enumerated.ShippingStatus.ACCEPTED;
import static com.cyberninja.model.entity.enumerated.ShippingStatus.INTRANSIT;
import static com.cyberninja.model.entity.enumerated.ShippingStatus.RETURNED;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.model.entity.Order;
import com.cyberninja.model.entity.Shipping;
import com.cyberninja.model.entity.converter.CustomerConverter;
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
	
	@Autowired private CustomerConverter customerConverter;
	
	/**
	 * Obtiene los envios del transportista en camino
	 */
	@Override
	public List<ShippingDTO> getShippings(Authentication auth) {
		List<Shipping> shippings = shippingRepo.findShippingsInTransit(Long.parseLong(auth.getName()));
		
		if (shippings.isEmpty()) {
			throw new ResponseStatusException(NOT_FOUND, "Shippings not founds");
		}
		
		List<ShippingDTO> dtos = shippingConverter.shippingsToShippingsDTO(shippings);
		
		for (ShippingDTO i : dtos) {
			i.setId(shippings.get(dtos.indexOf(i)).getId());
			
			i.setUuid(shippings.get(dtos.indexOf(i)).getUuid());
			
			i.setCustomer(customerConverter.CustomerToCustomerDTO(
					shippings.get(dtos.indexOf(i)).getUser().getCustomer()));
		}
		
		return dtos;
	}
	
	/**
	 * Añade un envio
	 * @throws ParseException 
	 */
	@Override
	public void addShipping(Order order) throws ParseException {
		Shipping shipping = new Shipping();
		shipping.setOrder(order);
		shipping.setStatus(ACCEPTED);
		shipping.setUuid(UUID.randomUUID().toString());

		order.setShipping(shipping);
	
		shippingRepo.save(shipping);
	}
	
	/**
	 * Devuelve un shipping
	 * 
	 * @param shipping
	 * @throws ParseException
	 */
	@Override
	public void returnShipping(ShippingDTO dto) {
		Shipping shipping = shippingRepo.findById(dto.getId())
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Shipping not found"));
		
		shipping.setStatus(RETURNED);
		shippingRepo.save(shipping);
	}

	/**
	 * Actualiza un envio
	 * @throws ParseException 
	 */
	@Override
	public ShippingDTO updateShipping(Authentication auth, String uuid, ShippingDTO dto, Boolean newShipping) throws ParseException {
		Shipping shipping = shippingRepo.findShippingByUuid(uuid)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Shipping not found"));
		
		if(newShipping && shipping.getStatus().equals(INTRANSIT)) {
			throw new ResponseStatusException(CONFLICT, "This shipping is already in transit");
		}
		
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
	 * Actualiza todos los envios que estan siendo llevados por el transportista 
	 */
	@Override
	public List<ShippingDTO> syncShippings(Authentication auth, ShippingDTO dto) {
		List<Shipping> shippings = shippingRepo.findShippingsInTransitForShipper(Long.parseLong(auth.getName()));
		
		if (shippings.isEmpty()) {
			throw new ResponseStatusException(NOT_FOUND, "Shippings not founds");
		}
		
		for (Shipping shipping : shippings) {
			shipping.setUpdateDate(new Date());
			shipping.setCounty(dto.getCounty());
			shipping.setState(dto.getState());
			shipping.setVillage(dto.getVillage());

			shippingRepo.save(shipping);
		}
		
		return shippingConverter.shippingsToShippingsDTO(shippings);
	}

}
