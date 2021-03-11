package com.cyberninja.services.business.impl;

import static com.cyberninja.common.ApplicationConstans.CUPON_ALPHANUM;
import static com.cyberninja.common.ApplicationConstans.CUPON_CODE_LENGHT;

import java.security.SecureRandom;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.cyberninja.model.entity.Coupon;
import com.cyberninja.services.business.CouponBusinessServiceI;

@Service
public class CouponBusinessServiceImpl implements CouponBusinessServiceI {

	/**
	 * Comprueba si un cupon es valido
	 * 
	 * @return boolean
	 */
	@Override
	public Boolean isCouponValid(Coupon coupon) {
		Boolean isValid = false;
		
		if (coupon.getUses() < coupon.getMaxUses() &&
		    !LocalDate.now().isAfter(coupon.getExpirationDate()) &&
		    coupon.getMaxUses() > 0) {
			
			isValid = true;
		}
	
		return isValid;
	}

	/**
	 * Asigna el codigo
	 */
	@Override
	public String generateCode(String code) {
		// Genera un código aleatorio si no tiene
		if (code == null) {
			code = "CN" + generateRandomCode();
		} 
		
		return code;
	}
	
	/**
	 * Genera un código alfanumerico de 8 caracteres
	 */
	private String generateRandomCode() {
		SecureRandom rnd = new SecureRandom();
		StringBuilder code = new StringBuilder(CUPON_CODE_LENGHT);

		for (int i = 0; i < CUPON_CODE_LENGHT; i++)
			code.append(CUPON_ALPHANUM.charAt(rnd.nextInt(CUPON_ALPHANUM.length())));

		return code.toString();
	}
}
