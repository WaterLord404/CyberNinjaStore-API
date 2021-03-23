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

	List<ProductDTO> getProducts() throws SQLException;

	ProductDTO getProductDTO(Long id);

	Product getProduct(Long id);

	ProductDTO createProduct(ProductDTO dto, List<MultipartFile> images) throws SQLException;

	ProductDTO deleteProduct(ProductDTO dto);

	ProductSize[] getSizes();

	ProductColour[] getColours();

	ProductCategory[] getCategories();

}
