package com.cyberninja.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cyberninja.model.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	@Query(value = 
			"SELECT o.* " +
			"FROM ORDERS o " + 
			"WHERE o.PURCHASE_DATE IS NULL " + 
			"AND o.CUSTOMER_ID = ?1", nativeQuery = true)
	Optional<Order> getUserCartOrder(Long id);
	
	@Query(value = 
			"SELECT o.* " +
			"FROM ORDERS o " + 
			"WHERE o.PURCHASE_DATE IS NOT NULL " + 
			"AND o.CUSTOMER_ID = ?1", nativeQuery = true)
	List<Order> getUserOrders(Long id);
}
