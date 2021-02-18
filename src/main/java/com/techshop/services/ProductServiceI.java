package com.techshop.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.techshop.model.entity.Product;
import com.techshop.model.entity.dto.ProductDTO;

public interface ProductServiceI {

	public List<ProductDTO> getProductsDTO() throws SQLException;
	
	public List<Product> getProducts(List<ProductDTO> dtos);

	public ProductDTO getProduct(Long id) throws SQLException;
	
	public ProductDTO createProduct(ProductDTO dto, List<MultipartFile> images) throws SQLException;

	public void deleteProduct(ProductDTO dto);

}
