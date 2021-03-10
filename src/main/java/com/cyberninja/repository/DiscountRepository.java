package com.cyberninja.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyberninja.model.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

	public Optional<Discount> findDiscountByIdAndActive(Long id, boolean active);

}
