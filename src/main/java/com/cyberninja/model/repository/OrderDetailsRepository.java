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
			"FROM CUSTOMERS c, ORDERS o, ORDERS_DETAILS od " +
			"WHERE c.CUSTOMER_ID = o.CUSTOMER_ID " +
			"AND o.ORDER_ID = od.ORDER_ID " +
			"AND o.PURCHASE_DATE IS NULL " +
			"AND c.CUSTOMER_ID = ?1", nativeQuery = true)
	List<OrderDetails> findUserProductsCart(Long id);
	
	@Query(value = 
			"SELECT od.* " +
			"FROM ORDERS o, ORDERS_DETAILS od " +
			"WHERE o.ORDER_ID = od.ORDER_ID " +
			"AND o.CUSTOMER_ID = ?1 " +
			"AND o.ORDER_ID = ?2", nativeQuery = true)
	List<OrderDetails> findUserProductsOnOrder(Long idUser, Long idOrder);

}

