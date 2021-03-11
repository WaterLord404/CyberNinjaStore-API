package com.cyberninja.model.entity.converter;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cyberninja.model.entity.Discount;
import com.cyberninja.model.entity.dto.DiscountDTO;
import com.cyberninja.services.business.InvoiceBusinessServiceI;

@Component
public class DiscountConverter {

	@Autowired
	private InvoiceBusinessServiceI invoiceBService;
	
	public Discount discountDTOToDiscount(DiscountDTO dto) {
		Discount discount = new Discount();

		discount.setValue(invoiceBService.roundDiscount(dto.getValue()));
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
}
