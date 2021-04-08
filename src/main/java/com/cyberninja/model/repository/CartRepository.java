package com.cyberninja.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyberninja.model.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
