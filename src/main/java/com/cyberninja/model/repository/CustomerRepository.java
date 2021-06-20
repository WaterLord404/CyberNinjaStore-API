package com.cyberninja.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cyberninja.model.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	@Query(value = 
			"SELECT * " + 
			"FROM CUSTOMERS c, USERS u " + 
		    "WHERE u.USER_ID = c.CUSTOMER_ID " +
		    "AND u.USERNAME = ?1", nativeQuery = true)
	Customer getCustomerByUserUsername(String username);

	@Query(value = 
			"SELECT c.* " + 
			"FROM CUSTOMERS c, ORDERS o, SHIPPINGS s " +
			"WHERE c.CUSTOMER_ID = o.CUSTOMER_ID " +
			"AND o.SHIPPING_ID = s.SHIPPING_ID " +
			"AND s.SHIPPING_ID = ?1", nativeQuery = true)
	Customer getCustomerOfShipping(Long id);
}
