package com.cyberninja.services.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyberninja.model.entity.OrderDetails;
import com.cyberninja.model.entity.Provider;
import com.cyberninja.services.business.ProviderBusinessServiceI;
import com.cyberninja.services.entity.ProviderServiceI;

@Service
public class ProviderBusinessServiceImpl implements ProviderBusinessServiceI {

	@Autowired private ProviderServiceI providerService;
	
	/**
	 * Calcula el beneficio dependiendo del contrato
	 */
	@Override
	public void calculateProfits(List<OrderDetails> ordersDetails) {
		for (OrderDetails i : ordersDetails) {
			Provider provider = i.getProduct().getProvider();

			if (provider != null) {

				switch (provider.getContract()) {
				case FIXED:
					providerService.plusProfits(
							calculateFixedProfits(i.getProduct().getTotalPrice(), i.getUnits()),
												  provider.getId());
					break;
				case ZERO:
					providerService.plusProfits(
							calculateZeroProfits(i.getProduct().getTotalPrice(), i.getUnits()), 
												 provider.getId());
					break;
				}
			}
		}
	}

	/**
	 * Obtiene de beneficio un 40% del precio total del producto
	 * 
	 * @param totalPrice
	 * @param units
	 */
	private Double calculateZeroProfits(Double totalPrice, Integer units) {
		return (double) Math.round((((totalPrice * units) / 100.0) * 40.0) * 100.0) / 100.0;
	}
	
	/**
	 * Obtiene de beneficio un 35% del precio total del producto.
	 * Por cada unidad aumenta un 0.2% los beneficios
	 * 
	 * @param totalPrice
	 * @param units
	 */
	private Double calculateFixedProfits(Double totalPrice, Integer units) {
		Double percentage = 35.0;
		
		for (int i = 0; i < units; i++) {
			percentage = percentage + 0.2;
		}
		
		return (double) Math.round((((totalPrice * units) / 100.0) * percentage) * 100.0) / 100.0;
	}

}
