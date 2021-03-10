package com.cyberninja.model.converter;

import org.springframework.stereotype.Component;

import com.cyberninja.model.Coupon;
import com.cyberninja.model.dto.CouponDTO;

@Component
public class CouponConverter {

	public Coupon couponDTOToCoupon(CouponDTO dto) {
		Coupon coupon = new Coupon();

		

		return coupon;
	}

	public CouponDTO couponToCouponDTO(Coupon coupon) {
		CouponDTO dto = new CouponDTO();

		

		return dto;
	}
}
