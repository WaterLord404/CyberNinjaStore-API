package com.cyberninja.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyberninja.model.entity.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

}
