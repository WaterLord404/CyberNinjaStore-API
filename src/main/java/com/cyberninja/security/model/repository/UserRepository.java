package com.cyberninja.security.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cyberninja.security.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findUserByIdAndEnabled(Long id, boolean enabled);
	
	Optional<User> findByUsername(String username);

	@Query(value = 
			"SELECT u.* " +
			"FROM USERS u , CUSTOMERS c " +
			"WHERE u.CUSTOMER_ID = c.CUSTOMER_ID " +
			"AND c.EMAIL = ?1", nativeQuery = true)
	Optional<User> findByEmail(String email);
	
	Optional<User> findUserByConfirmationTokenAndEnabled(String token, boolean enabled);
		
	@Query(value = 
			"SELECT u.* " +
			"FROM USERS u , CUSTOMERS c " +
			"WHERE u.CUSTOMER_ID = c.CUSTOMER_ID " +
			"AND c.EMAIL = ?1 " +
			"AND u.ENABLED = 1", nativeQuery = true)
	Optional<User> getUserByEmail(String email);
}
