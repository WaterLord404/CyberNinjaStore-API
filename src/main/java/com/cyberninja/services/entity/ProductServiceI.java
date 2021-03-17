package com.cyberninja.services.entity;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cyberninja.model.entity.Product;
import com.cyberninja.model.entity.dto.ProductDTO;
import com.cyberninja.model.entity.enumerated.ProductCategory;
import com.cyberninja.model.entity.enumerated.ProductColour;
import com.cyberninja.model.entity.enumerated.ProductSize;

public interface ProductServiceI {

	public List<ProductDTO> getProducts() throws SQLException;

	public ProductDTO getProductDTO(Long id);
	
	public Product getProduct(Long id);

	public ProductDTO createProduct(ProductDTO dto, List<MultipartFile> images) throws SQLException;

	public ProductDTO deleteProduct(ProductDTO dto);

	public ProductSize[] getSizes();

	public ProductColour[] getColours();

	public ProductCategory[] getCategories();

}
