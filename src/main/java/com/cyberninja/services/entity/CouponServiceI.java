package com.cyberninja.services.entity;

import com.cyberninja.model.entity.Coupon;
import com.cyberninja.model.entity.dto.CouponDTO;

public interface CouponServiceI {

	CouponDTO getCoupon(String couponCode);
	
	Coupon getValidCouponByCode(String couponCode);

	CouponDTO addCoupon(CouponDTO dto);

	void useCoupon(Coupon coupon);
	
	void deleteCoupon(Coupon coupon);

}
