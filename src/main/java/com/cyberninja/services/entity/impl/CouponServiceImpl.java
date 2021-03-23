package com.cyberninja.services.entity.impl;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.model.entity.Coupon;
import com.cyberninja.model.entity.converter.CouponConverter;
import com.cyberninja.model.entity.dto.CouponDTO;
import com.cyberninja.model.repository.CouponRepository;
import com.cyberninja.services.business.CouponBusinessServiceI;
import com.cyberninja.services.entity.CouponServiceI;
import com.cyberninja.services.entity.DiscountServiceI;

@Service
public class CouponServiceImpl implements CouponServiceI {

	@Autowired
	private CouponRepository couponRepo;

	@Autowired
	private CouponConverter couponConverter;

	@Autowired
	private CouponBusinessServiceI couponBService;

	@Autowired
	private DiscountServiceI discountService;

	/**
	 * Obtiene un cupon
	 */
	@Override
	public CouponDTO getCoupon(CouponDTO dto) {
		return couponConverter.couponToCouponDTO(
				getCouponByCode(couponConverter.couponDTOToCoupon(dto).getCode()));
	}

	/**
	 * Obtiene un cupon activo por codigo
	 */
	@Override
	public Coupon getCouponByCode(String couponCode) {
		return couponRepo.findCouponByCodeAndActive(couponCode, true)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
	}
	
	/**
	 * Crea un cupon
	 */
	@Override
	public CouponDTO addCoupon(CouponDTO dto) {
		Coupon coupon = couponConverter.couponDTOToCoupon(dto);

		coupon.setCode(couponBService.generateCode(dto.getCode()));

		// Valida el cupon
		if (!couponBService.isCouponValid(coupon)) {
			throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
		}

		// Asignacion del descuento
		coupon.setDiscount(discountService.getDiscount(dto.getDiscount().getId()));

		couponRepo.save(coupon);

		return couponConverter.couponToCouponDTO(coupon);
	}

	/**
	 * Suma 1 uso
	 */
	@Override
	public void useCoupon(Coupon coupon) {
		coupon.setUses(coupon.getUses() + 1);
		couponRepo.save(coupon);
	}

	/**
	 * Desactiva un cupon
	 */
	@Override
	public void deleteCoupon(Coupon coupon) {
		coupon.setActive(false);
		couponRepo.save(coupon);
	}

}
