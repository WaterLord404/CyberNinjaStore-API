package com.cyberninja.services.business.impl;

import static com.cyberninja.common.ApplicationConstans.IVA;
import static com.cyberninja.model.entity.enumerated.DiscountType.FIXED;
import static com.cyberninja.model.entity.enumerated.DiscountType.PERCENTAGE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyberninja.model.entity.Coupon;
import com.cyberninja.model.entity.Discount;
import com.cyberninja.model.entity.OrderDetails;
import com.cyberninja.services.business.OrderBusinessServiceI;
import com.cyberninja.services.entity.CouponServiceI;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessServiceI {

	@Autowired
	private CouponServiceI couponService;

	/**
	 * Calcula descuento
	 * 
	 * @return Product
	 */
	@Override
	public Double calculateDiscount(Double price, Discount discount) {
		Double totalPrice = 0.0;

		// El precio tiene que ser mayor que 0 para aplicar descuento
		if (price > 0) {
			// Si no tiene descuento el precio total es el calculo del iva
			if (discount == null) {
				totalPrice = price;
			}
			// Calculo tipo porcentaje
			else if (discount.getType().equals(PERCENTAGE)) {

				totalPrice = calculateDiscountPercentage(price, discount.getValue());
			}
			// Calculo tipo fijo
			else if (discount.getType().equals(FIXED)) {
				totalPrice = calculateDiscountFixed(price, discount.getValue());
			}
		}

		if (totalPrice <= 0) {totalPrice = 0.0;};
		
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
			// Calcula el descuento
			totalPrice = calculateDiscount(totalPrice, coupon.getDiscount());
			// Suma 1 al uso del cupon
			couponService.useCoupon(coupon);
		}

		// Shipping
		totalPrice = totalPrice + 5.99;
		
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
