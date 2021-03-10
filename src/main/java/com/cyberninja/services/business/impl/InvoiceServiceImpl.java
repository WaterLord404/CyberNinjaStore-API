package com.cyberninja.services.business.impl;

import static com.cyberninja.common.ApplicationConstans.IVA;

import org.springframework.stereotype.Service;

import com.cyberninja.model.entity.Product;
import com.cyberninja.services.business.InvoiceServiceI;

@Service
public class InvoiceServiceImpl implements InvoiceServiceI {

	/**
	 * Calcula el iva y descuento
	 * @return Product
	 */
	@Override
	public Product calculateInvoice(Product product) {
		// Calcular IVA
		Double price = calculateVat(product.getSalePrice());
		product.setPriceWoutDiscount(price);

		// Calcular descuento
		if (product.getDiscount() > 0 && product.getDiscount() <= 100) {
			product.setTotalPrice(calculateDiscount(price, product.getDiscount()));
		} else if (product.getDiscount() == 0) {
			product.setTotalPrice(price);
		}

		return product;
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
	 * Calcula el descuento
	 * 
	 * @return Double
	 */
	private Double calculateDiscount(Double price, Double discount) {
		return Math.round((price - (price * (discount / 100))) * 100.0) / 100.0;
	}

}
