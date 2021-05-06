package com.cyberninja.model.entity.converter;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.cyberninja.model.entity.Provider;
import com.cyberninja.model.entity.dto.ProviderDTO;

@Component
public class ProviderConverter {
	
	public ProviderDTO productToProductDTO(Provider provider) {
		ProviderDTO dto = new ProviderDTO();

		dto.setId(provider.getId());
		dto.setName(provider.getName());
		dto.setContract(provider.getContract());
		
		return dto;
	}
	
	public Provider productDTOToProduct(ProviderDTO dto) {
		Provider provider = new Provider();

		provider.setName(dto.getName());
		provider.setContract(dto.getContract());
		provider.setCreationDate(LocalDateTime.now());
		
		return provider;
	}
}
