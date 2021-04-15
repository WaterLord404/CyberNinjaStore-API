package com.cyberninja.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cyberninja.model.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findProductsByActive(boolean active);

	@Query(value = 
			"SELECT p.* " +
			"FROM PRODUCTS p, PRODUCTS_category pc " +
			"WHERE p.PRODUCT_ID  = pc.PRODUCTS_PRODUCT_ID " +
			"AND p.ACTIVE IS TRUE " +
			"AND pc.CATEGORY = ?1", nativeQuery = true)
	List<Product> findProfductsActiveCategory(String category);
	
	Optional<Product> findProductByIdAndActive(Long id, boolean active);
}
