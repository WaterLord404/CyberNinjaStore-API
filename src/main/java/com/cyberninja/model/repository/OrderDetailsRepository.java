package com.cyberninja.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cyberninja.model.entity.OrderDetails;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

	@Query(value = 
			"SELECT od.* " +
			"FROM CARTS ca, ORDERS_DETAILS od " +
			"WHERE ca.CART_ID = od.CART_ID " +
			"AND od.ORDER_ID IS NULL " +
			"AND od.CART_ID = ?1", nativeQuery = true)
	List<OrderDetails> userCart(Long id);
}
