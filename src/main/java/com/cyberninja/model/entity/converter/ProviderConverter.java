package com.cyberninja.model.entity.converter;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.cyberninja.model.entity.Provider;
import com.cyberninja.model.entity.dto.ProviderDTO;

@Component
public class ProviderConverter {
	
	public ProviderDTO providerToProviderDTO(Provider provider) {
		ProviderDTO dto = new ProviderDTO();

		dto.setId(provider.getId());
		dto.setName(provider.getName());
		dto.setContract(provider.getContract());
		
		return dto;
	}
	
	public Provider providerDTOToProvider(ProviderDTO dto) {
		Provider provider = new Provider();

		provider.setName(dto.getName());
		provider.setContract(dto.getContract());
		provider.setCreationDate(LocalDate.now());
		provider.setProfits(0.0);
		
		return provider;
	}
}
