package com.cyberninja.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cyberninja.model.entity.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long>{

	@Query(value = 
			"SELECT prov.* " + 
			"FROM PROVIDERS prov, PRODUCTS prod " + 
			"WHERE prov.PROVIDER_ID = prod.PROVIDER_ID " + 
			"AND prod.PRODUCT_ID = ?1", nativeQuery = true)
	Optional<Provider> findProviderByProduct(Long id);
}
