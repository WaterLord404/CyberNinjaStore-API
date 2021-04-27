package com.cyberninja.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cyberninja.model.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Optional<Product> findProductByIdAndActive(Long id, boolean active);

	@Query(value = 
			"SELECT p.* " + 
			"FROM PRODUCTS p " + 
			"WHERE p.ACTIVE IS TRUE " + 
			"ORDER BY p.PRODUCT_ID DESC " +
			"LIMIT ?1, ?2", nativeQuery = true)
	List<Product> findProductsByActiveOrderByIdDesc(Integer actualPage, Integer finishPage);
	
	@Query(value = 
			"SELECT p.* " + 
			"FROM PRODUCTS p " + 
			"WHERE p.ACTIVE IS TRUE " + 
			"ORDER BY p.TOTAL_PRICE DESC " +
			"LIMIT ?1, ?2", nativeQuery = true)
	List<Product> findProductsByActiveOrderByTotalPriceDesc(Integer actualPage, Integer finishPage);
	
	@Query(value = 
			"SELECT p.* " + 
			"FROM PRODUCTS p " + 
			"WHERE p.ACTIVE IS TRUE " + 
			"ORDER BY p.TOTAL_PRICE ASC " +
			"LIMIT ?1, ?2", nativeQuery = true)
	List<Product> findProductsByActiveOrderByTotalPriceAsc(Integer actualPage, Integer finishPage);
	
	@Query(value = 
			"SELECT p.* " + 
			"FROM PRODUCTS p " + 
			"WHERE p.ACTIVE IS TRUE " + 
			"ORDER BY p.STARS DESC " +
			"LIMIT ?1, ?2", nativeQuery = true)
	List<Product> findProductsByActiveOrderByStarsDesc(Integer actualPage, Integer finishPage);

	@Query(value = 
			"SELECT p.* " + 
			"FROM PRODUCTS p, PRODUCTS_category pc " + 
			"WHERE p.PRODUCT_ID = pc.PRODUCTS_PRODUCT_ID " + 
			"AND p.ACTIVE IS TRUE " + 
			"AND pc.CATEGORY = ?1 " + 
			"ORDER BY p.STARS DESC " +
			"LIMIT ?2, ?3", nativeQuery = true)
	List<Product> findProductsActiveCategoryPopularity(String category, Integer actualPage, Integer finishPage);
	
	@Query(value = 
			"SELECT p.* " + 
			"FROM PRODUCTS p, PRODUCTS_category pc " + 
			"WHERE p.PRODUCT_ID = pc.PRODUCTS_PRODUCT_ID " + 
			"AND p.ACTIVE IS TRUE " + 
			"AND pc.CATEGORY = ?1 " + 
			"ORDER BY p.PRODUCT_ID DESC " +
			"LIMIT ?2, ?3", nativeQuery = true)
	List<Product> findProductsActiveCategoryDesc(String category, Integer actualPage, Integer finishPage);
	
	
	@Query(value = 
			"SELECT p.* " + 
			"FROM PRODUCTS p, PRODUCTS_category pc " + 
			"WHERE p.PRODUCT_ID = pc.PRODUCTS_PRODUCT_ID " + 
			"AND p.ACTIVE IS TRUE " + 
			"AND pc.CATEGORY = ?1 " + 
			"ORDER BY p.TOTAL_PRICE DESC " +
			"LIMIT ?2, ?3", nativeQuery = true)
	List<Product> findProductsActiveCategoryPriceDesc(String category, Integer actualPage, Integer finishPage);
	
	@Query(value = 
			"SELECT p.* " + 
			"FROM PRODUCTS p, PRODUCTS_category pc " + 
			"WHERE p.PRODUCT_ID = pc.PRODUCTS_PRODUCT_ID " + 
			"AND p.ACTIVE IS TRUE " + 
			"AND pc.CATEGORY = ?1 " + 
			"ORDER BY p.TOTAL_PRICE ASC " +
			"LIMIT ?2, ?3", nativeQuery = true)
	List<Product> findProductsActiveCategoryPriceAsc(String category, Integer actualPage, Integer finishPage);

}
