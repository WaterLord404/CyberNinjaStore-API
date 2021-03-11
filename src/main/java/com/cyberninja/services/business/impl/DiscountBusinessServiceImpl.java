package com.cyberninja.services.business.impl;

import static com.cyberninja.model.entity.enumerated.DiscountType.FIXED;
import static com.cyberninja.model.entity.enumerated.DiscountType.PERCENTAGE;

import org.springframework.stereotype.Service;

import com.cyberninja.model.entity.Discount;
import com.cyberninja.services.business.DiscountBusinessServiceI;

@Service
public class DiscountBusinessServiceImpl implements DiscountBusinessServiceI {

	@Override
	public Boolean isDiscountValid(Discount discount) {
		Boolean isValid = false;

		if (discount.getType().equals(PERCENTAGE) &&
			discount.getValue() > 0.0 &&
			discount.getValue() <= 100.0) {

			isValid = true;
		}

		if (discount.getType().equals(FIXED) &&
			discount.getValue() > 0.0) {
			
			isValid = true;
		}
		
		return isValid;
	}

}
