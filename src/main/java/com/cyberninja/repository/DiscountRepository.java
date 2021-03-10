package com.cyberninja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyberninja.model.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

}
