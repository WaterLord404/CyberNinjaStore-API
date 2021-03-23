package com.cyberninja.services.entity;

import java.util.List;

import com.cyberninja.model.entity.Discount;
import com.cyberninja.model.entity.dto.DiscountDTO;
import com.cyberninja.model.entity.dto.ProductDTO;

public interface DiscountServiceI {

	DiscountDTO addDiscount(DiscountDTO dto);

	Discount getDiscount(Long id);

	ProductDTO updateDiscount(Long productId, Long discountId);

	List<DiscountDTO> getDiscounts();
}
