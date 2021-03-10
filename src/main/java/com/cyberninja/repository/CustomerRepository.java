package com.cyberninja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cyberninja.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	@Query(value = 
			"SELECT * " + 
			"FROM CUSTOMERS c, USERS u " + 
		    "WHERE u.USER_ID = c.CUSTOMER_ID " +
		    "AND u.USERNAME = ?1", nativeQuery = true)
	Customer getCustomerByUserUsername(String username);
}
