package com.cyberninja.services.business.impl;

import static com.cyberninja.common.ApplicationConstans.IVA;
import static com.cyberninja.model.enumerated.DiscountType.FIXED;
import static com.cyberninja.model.enumerated.DiscountType.PERCENTAGE;

import org.springframework.stereotype.Service;

import com.cyberninja.model.Product;
import com.cyberninja.services.business.InvoiceServiceI;

@Service
public class InvoiceServiceImpl implements InvoiceServiceI {

	/**
	 * Calcula el iva y descuento
	 * 
	 * @return Product
	 */
	@Override
	public Product calculateInvoice(Product product) {
		Double discount = 0.0;

		// Calcular IVA
		Double price = calculateVat(product.getSalePrice());
		product.setPriceWoutDiscount(price);

		// Si no tiene descuento el precio total es el calculo del iva
		if (product.getDiscount() == null) {
			product.setTotalPrice(price);

			// Redondeo del descuento
		} else if (product.getDiscount() != null) {
			discount = roundDiscount(product.getDiscount().getValue());

			// Calculo tipo porcentaje
		} else if (product.getDiscount().getType().equals(PERCENTAGE)) {
			product.setTotalPrice(calculateDiscountPercentage(price, discount));

			// Calculo tipo fijo
		} else if (product.getDiscount().getType().equals(FIXED)) {
			product.setTotalPrice(calculateDiscountFixed(price, discount));
		}

		return product;
	}

	/**
	 * Redondea el descuento con 2 decimales
	 * 
	 * @param discount
	 * @return
	 */
	private Double roundDiscount(Double discount) {
		return Math.round(discount * 100.0) / 100.0;
	}

	/**
	 * Calcula el precio con IVA
	 * 
	 * @return Double
	 */
	private Double calculateVat(Double salePrice) {
		return Math.round((salePrice + (salePrice * IVA)) * 100.0) / 100.0;
	}

	/**
	 * Calcula el descuento por porcentaje
	 * 
	 * @return Double
	 */
	private Double calculateDiscountPercentage(Double price, Double discount) {
		return Math.round((price - (price * (discount / 100))) * 100.0) / 100.0;
	}

	/**
	 * Calcula el descuento por capital
	 * 
	 * @return Double
	 */
	private Double calculateDiscountFixed(Double price, Double discount) {
		return Math.round((price - discount) * 100.0) / 100.0;
	}
}
