package com.cyberninja.services.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyberninja.model.Discount;
import com.cyberninja.model.converter.DiscountConverter;
import com.cyberninja.model.dto.DiscountDTO;
import com.cyberninja.repository.DiscountRepository;
import com.cyberninja.services.entity.DiscountServiceI;

@Service
public class DiscountServiceImpl implements DiscountServiceI {

	@Autowired
	private DiscountRepository discountRepo;

	@Autowired
	private DiscountConverter discountConverter;
	
	/**
	 * Crea un descuento
	 */
	@Override
	public DiscountDTO addDiscount(DiscountDTO dto) {
		Discount discount = discountConverter.discountDTOToDiscount(dto);
		
		discountRepo.save(discount);
		
		return discountConverter.discountToDiscountDTO(discount);
	}

}
