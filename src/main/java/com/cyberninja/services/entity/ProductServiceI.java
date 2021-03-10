package com.cyberninja.services.entity;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cyberninja.model.Product;
import com.cyberninja.model.dto.ProductDTO;

public interface ProductServiceI {

	public List<ProductDTO> getProducts() throws SQLException;

	public ProductDTO getProductDTO(Long id);
	
	public Product getProduct(Long id);

	public ProductDTO createProduct(ProductDTO dto, List<MultipartFile> images) throws SQLException;

	public void deleteProduct(ProductDTO dto);

}
