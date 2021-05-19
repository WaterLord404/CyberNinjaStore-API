package com.cyberninja.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cyberninja.model.entity.Shipping;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {
	
	@Query(value = 
			"SELECT s.* " +
			"FROM SHIPPINGS s " +
			"WHERE  s.STATUS = 'INTRANSIT' " +
			"AND s.USER_SHIPPER_ID = ?1", nativeQuery = true)
	List<Shipping> findShippingsInTransitForShipper(Long id);
}
