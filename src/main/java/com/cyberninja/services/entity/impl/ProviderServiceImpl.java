package com.cyberninja.services.entity.impl;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.model.entity.Product;
import com.cyberninja.model.entity.Provider;
import com.cyberninja.model.entity.converter.ProviderConverter;
import com.cyberninja.model.entity.dto.ProviderDTO;
import com.cyberninja.model.repository.ProviderRepository;
import com.cyberninja.services.entity.ProductServiceI;
import com.cyberninja.services.entity.ProviderServiceI;

@Service
public class ProviderServiceImpl implements ProviderServiceI {

	@Autowired private ProviderRepository providerRepo;
	
	@Autowired private ProviderConverter providerConverter;
	
	@Autowired private ProductServiceI productService;
	
	@Override
	public ProviderDTO getProviderOfProduct(Long idProduct) {
		return providerConverter.providerToProviderDTO(
				providerRepo.findProviderByProduct(idProduct)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Provider not found")));
	}

	@Override
	public ProviderDTO addProvider(ProviderDTO dto) {
		return providerConverter.providerToProviderDTO(
				providerRepo.save(
						providerConverter.providerDTOToProvider(dto)));
	}

	/**
	 * Suma los beneficios a los ya existentes
	 */
	@Override
	public void plusProfits(Double profits, Long idProvider) {
		Provider provider = providerRepo.findById(idProvider)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Provider not found"));
		
		provider.setProfits(provider.getProfits() + profits);
		
		providerRepo.save(provider);
	}

	@Override
	public ProviderDTO setProviderToProduct(Long idProvider, Long idProduct) {
		Provider provider = providerRepo.findById(idProvider)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Provider not found"));
		
		Product product = productService.getProduct(idProduct);
		
		product.setProvider(provider);
		
		return providerConverter.providerToProviderDTO(providerRepo.save(provider));
	}

}
