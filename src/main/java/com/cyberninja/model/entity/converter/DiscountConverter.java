package com.cyberninja.model.entity.converter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cyberninja.model.entity.Discount;
import com.cyberninja.model.entity.dto.DiscountDTO;
import com.cyberninja.services.business.OrderBusinessServiceI;

@Component
public class DiscountConverter {

	@Autowired
	private OrderBusinessServiceI orderBService;
	
	public Discount discountDTOToDiscount(DiscountDTO dto) {
		Discount discount = new Discount();

		discount.setValue(orderBService.roundDiscount(dto.getValue()));
		discount.setType(dto.getType());
		discount.setCreationDate(LocalDate.now());
		discount.setActive(true);

		return discount;
	}

	public DiscountDTO discountToDiscountDTO(Discount discount) {
		DiscountDTO dto = new DiscountDTO();

		dto.setId(discount.getId());
		dto.setValue(discount.getValue());
		dto.setType(discount.getType());

		return dto;
	}
	
	public List<DiscountDTO> discountsToDiscountsDTO(List<Discount> discounts) {
		List<DiscountDTO> dtos = new ArrayList<>();
		
		for (Discount discount : discounts) {
			dtos.add(discountToDiscountDTO(discount));
		}

		return dtos;
	}
}
