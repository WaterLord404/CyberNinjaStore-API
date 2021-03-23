package com.cyberninja.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyberninja.model.entity.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

	Optional<Discount> findDiscountByIdAndActive(Long id, boolean active);

	List<Discount> findDiscountByActive(boolean active);

}
