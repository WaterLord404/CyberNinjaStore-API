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
}
