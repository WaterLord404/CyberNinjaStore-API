package com.cyberninja.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cyberninja.model.entity.Product;
import com.cyberninja.model.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	List<Review> findReviewByProduct(Product product);

	@Query(value = 
			"SELECT AVG(r.VALUE) " + 
			"FROM REVIEWS r " + 
			"WHERE r.PRODUCT_ID = ?1", nativeQuery = true)
	Double calculateProductAVG(Long productId);

	@Query(value = 
		"SELECT " +
			"r.* " +
		"FROM " +
			"CUSTOMERS c, " +
			"ORDERS o, " +
			"ORDERS_DETAILS od, " +
			"PRODUCTS p, " +
			"REVIEWS r " +
		"WHERE " +
			"c.CUSTOMER_ID = o.CUSTOMER_ID " +
			"AND o.ORDER_ID = od.ORDER_ID " +
			"AND od.PRODUCT_ID = p.PRODUCT_ID " +
			"AND p.PRODUCT_ID = r.PRODUCT_ID " +
			"AND p.PRODUCT_ID = ?1 " +
			"AND c.CUSTOMER_ID = ?2", nativeQuery = true)
	Review findCustomerReview(Long productId, Long id);
}
