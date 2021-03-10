package com.cyberninja.services.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyberninja.model.Coupon;
import com.cyberninja.model.converter.CouponConverter;
import com.cyberninja.model.dto.CouponDTO;
import com.cyberninja.repository.CouponRepository;
import com.cyberninja.services.entity.CouponServiceI;

@Service
public class CouponServiceImpl implements CouponServiceI {

	@Autowired
	private CouponRepository couponRepo;

	@Autowired
	private CouponConverter couponConverter;

	/**
	 * Crea un cupon
	 */
	@Override
	public CouponDTO addCoupon(CouponDTO dto) {
		Coupon coupon = couponConverter.couponDTOToCoupon(dto);

		couponRepo.save(coupon);

		return couponConverter.couponToCouponDTO(coupon);
	}
}
