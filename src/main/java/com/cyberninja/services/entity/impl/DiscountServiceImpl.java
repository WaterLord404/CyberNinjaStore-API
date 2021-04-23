package com.cyberninja.services.entity.impl;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.model.entity.Discount;
import com.cyberninja.model.entity.Product;
import com.cyberninja.model.entity.converter.DiscountConverter;
import com.cyberninja.model.entity.converter.ProductConverter;
import com.cyberninja.model.entity.dto.DiscountDTO;
import com.cyberninja.model.entity.dto.ProductDTO;
import com.cyberninja.model.repository.DiscountRepository;
import com.cyberninja.model.repository.ProductRepository;
import com.cyberninja.services.business.DiscountBusinessServiceI;
import com.cyberninja.services.business.OrderBusinessServiceI;
import com.cyberninja.services.entity.DiscountServiceI;
import com.cyberninja.services.entity.ProductServiceI;

@Service
public class DiscountServiceImpl implements DiscountServiceI {

	@Autowired private DiscountRepository discountRepo;

	@Autowired private DiscountConverter discountConverter;

	@Autowired private ProductServiceI productService;

	@Autowired private ProductConverter productConverter;

	@Autowired private OrderBusinessServiceI orderBService;

	@Autowired private ProductRepository productRepo;

	@Autowired private DiscountBusinessServiceI discountBService;

	/**
	 * Obtiene todos los descuentos activos
	 */
	@Override
	public List<DiscountDTO> getDiscounts() {
		return discountConverter.discountsToDiscountsDTO(discountRepo.findDiscountByActive(true));
	}
	
	/**
	 * Crea un descuento
	 */
	@Override
	public DiscountDTO addDiscount(DiscountDTO dto) {
		Discount discount = discountConverter.discountDTOToDiscount(dto);

		// Valida el descuento
		if (!discountBService.isDiscountValid(discount)) {
			throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
		}

		discountRepo.save(discount);

		return discountConverter.discountToDiscountDTO(discount);
	}

	/**
	 * Obtiene un descuento activo
	 */
	@Override
	public Discount getDiscount(Long id) {
		return discountRepo.findDiscountByIdAndActive(id, true)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
	}

	/**
	 * Actualiza el descuento de un producto
	 */
	@Override
	public ProductDTO updateDiscount(Long productId, Long discountId) {
		Product product = productService.getProduct(productId);

		if (discountId == 0) {
			product.setDiscount(null);
		} else {
			product.setDiscount(getDiscount(discountId));			
		}

		// Calcula el descuento
		product.setTotalPrice(orderBService.calculateDiscount(product.getPriceWithVat(), product.getDiscount()));

		productRepo.save(product);

		return productConverter.productToProductDTO(product);
	}

}
