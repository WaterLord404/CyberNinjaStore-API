package com.cyberninja.services.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyberninja.model.entity.Coupon;
import com.cyberninja.model.entity.converter.CouponConverter;
import com.cyberninja.model.entity.dto.CouponDTO;
import com.cyberninja.model.repository.CouponRepository;
import com.cyberninja.services.entity.CouponServiceI;
import com.cyberninja.services.utils.UtilsServiceI;

@Service
public class CouponServiceImpl implements CouponServiceI {

	@Autowired
	private CouponRepository couponRepo;

	@Autowired
	private CouponConverter couponConverter;
	
	@Autowired
	private UtilsServiceI utilsService;

	/**
	 * Crea un cupon
	 */
	@Override
	public CouponDTO addCoupon(CouponDTO dto) {
		Coupon coupon = couponConverter.couponDTOToCoupon(dto);

		// Genera un código aleatorio si no tiene
		if(dto.getCode() == null) {
			coupon.setCode("CN" + utilsService.generateRandomCode());			
		} else {
			coupon.setCode(dto.getCode());
		}

		couponRepo.save(coupon);

		return couponConverter.couponToCouponDTO(coupon);
	}
	
}
