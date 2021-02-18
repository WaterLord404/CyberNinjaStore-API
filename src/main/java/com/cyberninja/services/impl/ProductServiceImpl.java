package com.cyberninja.services.impl;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.model.entity.Product;
import com.cyberninja.model.entity.converter.ProductDTOConverter;
import com.cyberninja.model.entity.dto.ProductDTO;
import com.cyberninja.model.repository.ProductRepository;
import com.cyberninja.services.DocumentServiceI;
import com.cyberninja.services.ProductServiceI;

@Service
public class ProductServiceImpl implements ProductServiceI {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private ProductDTOConverter productConverter;
	
	@Autowired
	private DocumentServiceI documentService;

	/**
	 * Obtiene todos los productos con los documentos
	 * @return List ProductDTO
	 * @throws SQLException 
	 */
	@Override
	public List<ProductDTO> getProductsDTO() throws SQLException {
		List<ProductDTO> dtos = new ArrayList<>(); // Lista de productsDTO a retornar

		for (Product product : productRepo.findAll()) {
			
			ProductDTO productDTO = productConverter.productToProductDTO(product);
			productDTO.setDocuments(documentService.getDocumentsDTO(product.getDocuments()));

			dtos.add(productDTO);
		}

		if (dtos.isEmpty()) { throw new ResponseStatusException(NOT_FOUND); }

		return dtos;
	}

	@Override
	public List<Product> getProducts(List<ProductDTO> dtos) {
		List<Product> products = new ArrayList<>();

		for (ProductDTO productDTO : dtos) {
			products.add(productRepo.findById(productDTO.getId())
					     .orElseThrow(() -> new ResponseStatusException(NOT_FOUND)));
		}

		if (products.isEmpty()) { throw new ResponseStatusException(BAD_REQUEST); }
		
		return products;
	}
	
	/**
	 * Obtiene un producto con sus documentos
	 * @return ProductDTO
	 * @throws SQLException 
	 */
	@Override
	public ProductDTO getProduct(Long id) throws SQLException {
		Product product = productRepo.findById(id)
						  .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));

		ProductDTO dto = productConverter.productToProductDTO(product);
		dto.setDocuments(documentService.getDocumentsDTO(product.getDocuments()));

		return dto;
	}

	/**
	 * Crea un producto con sus documentos
	 * @return ProductDTO
	 * @throws SQLException 
	 */
	@Override
	public ProductDTO createProduct(ProductDTO dto, List<MultipartFile> images) throws SQLException {
		Product product = productConverter.productDTOToProduct(dto);
		product.setDocuments(documentService.createDocuments(images, product));
		
		productRepo.save(product);

		return getProduct(product.getId());
	}

	/**
	 * Borra un producto con sus documentos
	 * @return ProductDTO
	 */
	@Override
	public void deleteProduct(ProductDTO dto) {
		productRepo.deleteById(dto.getId());
	}

}
