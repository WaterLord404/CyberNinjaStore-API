package com.cyberninja.services.entity;

import java.util.List;

import com.cyberninja.model.entity.Discount;
import com.cyberninja.model.entity.dto.DiscountDTO;
import com.cyberninja.model.entity.dto.ProductDTO;

public interface DiscountServiceI {

	public DiscountDTO addDiscount(DiscountDTO dto);
	
	public Discount getDiscount(Long id);

	public ProductDTO updateDiscount(Long productId, Long discountId);

	public List<DiscountDTO> getDiscounts();
}
