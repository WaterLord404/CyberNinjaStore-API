package com.cyberninja.services.entity;

import com.cyberninja.model.entity.dto.ProviderDTO;

public interface ProviderServiceI {

	ProviderDTO getProviderOfProduct(Long idProduct);

	ProviderDTO addProvider(ProviderDTO dto);

}
