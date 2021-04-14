package com.cyberninja.model.entity.dto;

import com.cyberninja.model.entity.enumerated.ProviderContract;

public class ProviderDTO {
	
	private Long id;
	
	private String name;
	
	private ProviderContract contract;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProviderContract getContract() {
		return contract;
	}

	public void setContract(ProviderContract contract) {
		this.contract = contract;
	}
	
	
}
