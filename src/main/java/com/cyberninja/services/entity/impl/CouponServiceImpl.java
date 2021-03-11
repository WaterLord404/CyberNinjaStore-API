package com.cyberninja.services.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.model.entity.Coupon;
import com.cyberninja.model.entity.converter.CouponConverter;
import com.cyberninja.model.entity.dto.CouponDTO;
import com.cyberninja.model.repository.CouponRepository;
import com.cyberninja.services.business.CouponBusinessServiceI;
import com.cyberninja.services.entity.CouponServiceI;

@Service
public class CouponServiceImpl implements CouponServiceI {

	@Autowired
	private CouponRepository couponRepo;

	@Autowired
	private CouponConverter couponConverter;

	@Autowired
	private CouponBusinessServiceI couponBusinessService;

	/**
	 * Crea un cupon
	 */
	@Override
	public CouponDTO addCoupon(CouponDTO dto) {
		Coupon coupon = couponConverter.couponDTOToCoupon(dto);

		// Genera un código aleatorio si no tiene
		if (dto.getCode() == null) {
			coupon.setCode("CN" + couponBusinessService.generateRandomCode());
		} else {
			coupon.setCode(dto.getCode());
		}

		couponRepo.save(coupon);

		return couponConverter.couponToCouponDTO(coupon);
	}

	/**
	 * Obtiene un cupon
	 */
	@Override
	public Coupon getCoupon(String couponCode) {
		return couponRepo.findCouponByCodeAndActive(couponCode, true)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

}
