package com.cyberninja.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyberninja.model.entity.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long>{

}
