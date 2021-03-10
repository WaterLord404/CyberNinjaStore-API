package com.cyberninja.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyberninja.model.entity.Coupon;
import com.cyberninja.model.entity.converter.CouponConverter;
import com.cyberninja.model.entity.dto.CouponDTO;
import com.cyberninja.model.repository.CouponRepository;
import com.cyberninja.services.CouponServiceI;

@Service
public class CouponServiceImpl implements CouponServiceI {

	@Autowired
	private CouponRepository couponRepo;

	@Autowired
	private CouponConverter couponConverter;

	@Override
	public CouponDTO addCoupon(CouponDTO dto) {
		Coupon coupon = couponConverter.couponDTOToCoupon(dto);

		couponRepo.save(coupon);

		return couponConverter.couponToCouponDTO(coupon);
	}
}
