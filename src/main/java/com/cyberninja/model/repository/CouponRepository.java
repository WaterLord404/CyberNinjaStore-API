package com.cyberninja.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyberninja.model.entity.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

	public Optional<Coupon> findCouponByCodeAndActive(String couponCode, Boolean active);
	
	public Coupon findCouponByCode(String couponCode);

}
