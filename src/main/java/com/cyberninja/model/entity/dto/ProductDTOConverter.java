package com.cyberninja.model.entity.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cyberninja.model.entity.Product;

@Component
public class ProductDTOConverter {

	public Product productDTOToProduct(ProductDTO dto) {
		Product product = new Product();

		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		product.setSize(dto.getSize());
		product.setColour(dto.getColour());
		product.setCategory(dto.getCategory());
		product.setCreateTime(LocalDate.now());
		
		return product;
	}

	public ProductDTO productToProductDTO(Product product) {
		ProductDTO dto = new ProductDTO();
		
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setDescription(product.getDescription());
		dto.setPrice(product.getPrice());
		dto.setSize(product.getSize());
		dto.setColour(product.getColour());
		dto.setCategory(product.getCategory());
		
		return dto;
	}
	
	public List<ProductDTO> productsToProductsDTO(List<Product> products) {
		List<ProductDTO> dtos = new ArrayList<>();
		
		for (Product product : products) {
			dtos.add(productToProductDTO(product));
		}
		
		return dtos;
	}
}
