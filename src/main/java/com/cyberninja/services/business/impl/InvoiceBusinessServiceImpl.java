package com.cyberninja.services.business.impl;

import static com.cyberninja.common.ApplicationConstans.IVA;
import static com.cyberninja.model.entity.enumerated.DiscountType.FIXED;
import static com.cyberninja.model.entity.enumerated.DiscountType.PERCENTAGE;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.model.entity.Coupon;
import com.cyberninja.model.entity.Discount;
import com.cyberninja.model.entity.OrderDetails;
import com.cyberninja.services.business.CouponBusinessServiceI;
import com.cyberninja.services.business.InvoiceBusinessServiceI;

@Service
public class InvoiceBusinessServiceImpl implements InvoiceBusinessServiceI {

	@Autowired
	private CouponBusinessServiceI couponBService;

	/**
	 * Calcula el iva y descuento
	 * 
	 * @return Product
	 */
	@Override
	public Double calculateInvoice(Double priceWithVAT, Discount discount) {
		Double totalPrice = 0.0;

		// Si no tiene descuento el precio total es el calculo del iva
		if (discount == null) {
			totalPrice = priceWithVAT;
		}
		// Calculo tipo porcentaje
		else if (discount.getType().equals(PERCENTAGE)) {

			totalPrice = calculateDiscountPercentage(priceWithVAT, discount.getValue());
		}
		// Calculo tipo fijo
		else if (discount.getType().equals(FIXED)) {
			totalPrice = calculateDiscountFixed(priceWithVAT, discount.getValue());
		}

		return totalPrice;
	}

	/**
	 * Suma el precio de todos los productos
	 * 
	 * @param products
	 * @return
	 */
	@Override
	public Double calculateTotalPrice(List<OrderDetails> ordersDetails, Coupon coupon) {
		Double totalPrice = 0.0;

		for (OrderDetails orderDetails : ordersDetails) {
			totalPrice = totalPrice + (orderDetails.getUnits() * orderDetails.getProduct().getTotalPrice());
		}

	
		if (coupon != null) {
			// Valida el cupon
			if (couponBService.isCouponValid(coupon)) {
				totalPrice = calculateInvoice(totalPrice, coupon.getDiscount());
				
			} else {
				throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
			}
		}

		return Math.round(totalPrice * 100.0) / 100.0;
	}

	/**
	 * Redondea el descuento con 2 decimales
	 * 
	 * @param discount
	 * @return
	 */
	@Override
	public Double roundDiscount(Double discount) {
		return Math.round(discount * 100.0) / 100.0;
	}

	/**
	 * Calcula el precio con IVA
	 * 
	 * @return Double
	 */
	@Override
	public Double calculateVat(Double salePrice) {
		return Math.round((salePrice + (salePrice * IVA)) * 100.0) / 100.0;
	}

	/**
	 * Calcula el descuento por porcentaje
	 * 
	 * @return Double
	 */
	private Double calculateDiscountPercentage(Double priceWithVAT, Double discount) {
		return Math.round((priceWithVAT - (priceWithVAT * (discount / 100))) * 100.0) / 100.0;
	}

	/**
	 * Calcula el descuento por capital
	 * 
	 * @return Double
	 */
	private Double calculateDiscountFixed(Double priceWithVAT, Double discount) {
		return Math.round((priceWithVAT - discount) * 100.0) / 100.0;
	}

}
