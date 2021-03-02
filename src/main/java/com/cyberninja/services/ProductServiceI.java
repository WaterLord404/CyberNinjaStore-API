package com.cyberninja.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cyberninja.model.entity.Product;
import com.cyberninja.model.entity.dto.ProductDTO;

public interface ProductServiceI {

	public List<ProductDTO> getProducts() throws SQLException;
	
	public ProductDTO getProduct(Long id) throws SQLException;
	
	public ProductDTO createProduct(ProductDTO dto, List<MultipartFile> images) throws SQLException;

	public void deleteProduct(ProductDTO dto);

	public List<Product> findSelectedProducts(List<ProductDTO> dtos);

}
