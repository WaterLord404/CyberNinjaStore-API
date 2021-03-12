package com.cyberninja.services.business;

import java.util.List;

import com.cyberninja.model.entity.Coupon;
import com.cyberninja.model.entity.Discount;
import com.cyberninja.model.entity.OrderDetails;

public interface OrderBusinessServiceI {

	public Double calculateDiscount(Double priceWithVAT, Discount discount);

	public Double calculateTotalPrice(List<OrderDetails> ordersDetails, Coupon coupon);

	public Double roundDiscount(Double discount);

	public Double calculateVat(Double salePrice);

}
