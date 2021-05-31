package com.cyberninja.services.entity.impl;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.model.entity.Coupon;
import com.cyberninja.model.entity.converter.CouponConverter;
import com.cyberninja.model.entity.converter.DiscountConverter;
import com.cyberninja.model.entity.dto.CouponDTO;
import com.cyberninja.model.repository.CouponRepository;
import com.cyberninja.services.business.CouponBusinessServiceI;
import com.cyberninja.services.entity.CouponServiceI;
import com.cyberninja.services.entity.DiscountServiceI;

@Service
public class CouponServiceImpl implements CouponServiceI {

	@Autowired private CouponRepository couponRepo;

	@Autowired private CouponConverter couponConverter;

	@Autowired private CouponBusinessServiceI couponBService;

	@Autowired private DiscountServiceI discountService;

	@Autowired private DiscountConverter discountConverter;
	
	/**
	 * Obtiene un cupon con el descuento
	 */
	@Override
	public CouponDTO getCoupon(String couponCode) {
		Coupon coupon = getValidCouponByCode(couponCode);
		
		CouponDTO dto = couponConverter.couponToCouponDTO(coupon);
		
		dto.setDiscount(discountConverter.discountToDiscountDTO(
				discountService.getDiscount(
						coupon.getDiscount().getId())));
		
		return dto;
	}

	/**
	 * Obtiene un cupon activo y valido por el codigo
	 */
	@Override
	public Coupon getValidCouponByCode(String couponCode) {
		Coupon coupon = couponRepo.findCouponByCodeAndActive(couponCode, true)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Coupon not found"));
		
		if (!couponBService.isCouponValid(coupon)) {
			deleteCoupon(coupon);
			throw new ResponseStatusException(NOT_FOUND, "Coupon not found");
		}
		
		return coupon;
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
			throw new ResponseStatusException(UNPROCESSABLE_ENTITY, "Coupon not valid");
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
