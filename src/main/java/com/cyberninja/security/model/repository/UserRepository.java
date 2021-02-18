package com.cyberninja.security.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyberninja.security.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public Optional<User> findByUsername(String username);
	
	public User findUserByUsername(String username);
}
