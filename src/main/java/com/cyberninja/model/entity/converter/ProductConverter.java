package com.cyberninja.model.entity.converter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cyberninja.model.entity.Product;
import com.cyberninja.model.entity.dto.ProductDTO;

@Component
public class ProductConverter {

	@Autowired
	private DiscountConverter discountConverter;

	@Autowired
	private DocumentConverter documentConverter;

	public Product productDTOToProduct(ProductDTO dto) {
		Product product = new Product();

		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setPurchasePrice(dto.getPurchasePrice());
		product.setSalePrice(dto.getSalePrice());
		product.setSize(dto.getSize());
		product.setColour(dto.getColour());
		product.setCategory(dto.getCategory());
		product.setCreationDate(LocalDate.now());
		product.setActive(true);

		return product;
	}

	public ProductDTO productToProductDTO(Product product) {
		ProductDTO dto = new ProductDTO();

		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setDescription(product.getDescription());
		dto.setTotalPrice(product.getTotalPrice());
		dto.setPriceWoutDiscount(product.getPriceWoutDiscount());
		dto.setSize(product.getSize());
		dto.setColour(product.getColour());
		dto.setCategory(product.getCategory());
		dto.setActive(product.isActive());
		dto.setDocuments(documentConverter.getDocumentsDTO(product.getDocuments()));

		if (product.getDiscount() != null) {
			dto.setDiscount(discountConverter.discountToDiscountDTO(product.getDiscount()));
		}

		return dto;
	}

	/**
	 * Transforma una lista de Product a ProductDTO
	 */
	public List<ProductDTO> productsToProductsDTO(List<Product> products) {
		List<ProductDTO> dtos = new ArrayList<>();

		for (Product product : products) {
			dtos.add(productToProductDTO(product));
		}

		return dtos;
	}

}
