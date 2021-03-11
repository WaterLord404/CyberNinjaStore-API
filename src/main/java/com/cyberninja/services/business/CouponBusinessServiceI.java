package com.cyberninja.services.business;

import com.cyberninja.model.entity.Coupon;

public interface CouponBusinessServiceI {

	public Boolean isCouponValid(Coupon coupon);

	public String generateRandomCode();
}
