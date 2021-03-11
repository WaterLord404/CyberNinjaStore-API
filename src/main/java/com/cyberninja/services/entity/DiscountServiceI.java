package com.cyberninja.services.entity;

import com.cyberninja.model.entity.Discount;
import com.cyberninja.model.entity.dto.DiscountDTO;
import com.cyberninja.model.entity.dto.ProductDTO;

public interface DiscountServiceI {

	public DiscountDTO addDiscount(DiscountDTO dto);
	
	public Discount getDiscount(Long id);

	public ProductDTO setDiscount(Long productId, Long discountId);
}
