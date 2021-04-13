package com.cyberninja.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cyberninja.model.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	@Query(value = 
			"SELECT ca.* " + 
			"FROM CUSTOMERS c, CARTS ca " + 
			"WHERE c.CART_ID = ca.CART_ID " + 
			"AND c.CUSTOMER_ID = ?1", nativeQuery = true)
	Optional<Cart> getCart(Long id);
}
