package com.cyberninja.model.converter;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.cyberninja.model.Coupon;
import com.cyberninja.model.dto.CouponDTO;

@Component
public class CouponConverter {

	public Coupon couponDTOToCoupon(CouponDTO dto) {
		Coupon coupon = new Coupon();

		coupon.setCode(dto.getCode());
		coupon.setCreationDate(LocalDate.now());
		coupon.setExpirationDate(dto.getExpirationDate());
		coupon.setUses(0);
		coupon.setMaxUses(dto.getMaxUses());
		coupon.setActive(true);

		return coupon;
	}

	public CouponDTO couponToCouponDTO(Coupon coupon) {
		CouponDTO dto = new CouponDTO();

		dto.setCode(coupon.getCode());

		return dto;
	}
}
