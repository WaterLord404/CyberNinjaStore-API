package com.cyberninja.services.entity.impl;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.model.Discount;
import com.cyberninja.model.Product;
import com.cyberninja.model.converter.DiscountConverter;
import com.cyberninja.model.converter.ProductConverter;
import com.cyberninja.model.dto.DiscountDTO;
import com.cyberninja.model.dto.ProductDTO;
import com.cyberninja.repository.DiscountRepository;
import com.cyberninja.repository.ProductRepository;
import com.cyberninja.services.business.InvoiceServiceI;
import com.cyberninja.services.entity.DiscountServiceI;
import com.cyberninja.services.entity.ProductServiceI;

@Service
public class DiscountServiceImpl implements DiscountServiceI {

	@Autowired
	private DiscountRepository discountRepo;

	@Autowired
	private DiscountConverter discountConverter;

	@Autowired
	private ProductServiceI productService;

	@Autowired
	private ProductConverter productConverter;

	@Autowired
	private InvoiceServiceI invoiceService;
	
	@Autowired
	private ProductRepository productRepo;
	
	/**
	 * Crea un descuento
	 */
	@Override
	public DiscountDTO addDiscount(DiscountDTO dto) {
		Discount discount = discountConverter.discountDTOToDiscount(dto);

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

	@Override
	public ProductDTO setDiscount(Long productId, Long discountId) {
		Product product = productService.getProduct(productId);

		product.setDiscount(getDiscount(discountId));

		// Calcula iva y descuento
		product = invoiceService.calculateInvoice(product);
		
		productRepo.save(product);

		return productConverter.productToProductDTO(product);
	}

}
