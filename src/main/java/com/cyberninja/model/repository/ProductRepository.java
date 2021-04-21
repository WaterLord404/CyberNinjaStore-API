package com.cyberninja.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cyberninja.model.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findProductsByActiveOrderByIdDesc(boolean active);
	
	List<Product> findProductsByActiveOrderByTotalPriceDesc(boolean active);

	List<Product> findProductsByActiveOrderByTotalPriceAsc(boolean active);

	List<Product> findProductsByActiveOrderByStarsDesc(boolean active);

	Optional<Product> findProductByIdAndActive(Long id, boolean active);

	@Query(value = 
			"SELECT p.* " + 
			"FROM PRODUCTS p, PRODUCTS_category pc " + 
			"WHERE p.PRODUCT_ID = pc.PRODUCTS_PRODUCT_ID " + 
			"AND p.ACTIVE IS TRUE " + 
			"AND pc.CATEGORY = ?1 " + 
			"ORDER BY p.STARS DESC", nativeQuery = true)
	List<Product> findProductsActiveCategoryPopularity(String category);
	
	@Query(value = 
			"SELECT p.* " +
			"FROM PRODUCTS p, PRODUCTS_category pc " +
			"WHERE p.PRODUCT_ID  = pc.PRODUCTS_PRODUCT_ID " +
			"AND p.ACTIVE IS TRUE " +
			"AND pc.CATEGORY = ?1 " +
			"ORDER BY p.PRODUCT_ID DESC", nativeQuery = true)
	List<Product> findProductsActiveCategoryDesc(String category);
	
	
	@Query(value = 
			"SELECT p.* " +
			"FROM PRODUCTS p, PRODUCTS_category pc " +
			"WHERE p.PRODUCT_ID  = pc.PRODUCTS_PRODUCT_ID " +
			"AND p.ACTIVE IS TRUE " +
			"AND pc.CATEGORY = ?1 " + 
			"ORDER BY p.TOTAL_PRICE DESC", nativeQuery = true)
	List<Product> findProductsActiveCategoryPriceDesc(String category);
	
	@Query(value = 
			"SELECT p.* " +
			"FROM PRODUCTS p, PRODUCTS_category pc " +
			"WHERE p.PRODUCT_ID  = pc.PRODUCTS_PRODUCT_ID " +
			"AND p.ACTIVE IS TRUE " +
			"AND pc.CATEGORY = ?1 " +
			"ORDER BY p.TOTAL_PRICE ASC", nativeQuery = true)
	List<Product> findProductsActiveCategoryPriceAsc(String category);

}
