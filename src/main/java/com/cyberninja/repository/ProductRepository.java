package com.cyberninja.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyberninja.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	public List<Product> findProductsByActive(boolean active);
	
	public Optional<Product> findProductByIdAndActive(Long id, boolean active);
}
