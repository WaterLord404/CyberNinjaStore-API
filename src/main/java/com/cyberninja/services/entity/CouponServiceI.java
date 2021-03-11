package com.cyberninja.services.entity;

import com.cyberninja.model.entity.Coupon;
import com.cyberninja.model.entity.dto.CouponDTO;

public interface CouponServiceI {

	public CouponDTO addCoupon(CouponDTO dto);

	public Coupon getCoupon(String couponCode);
}
