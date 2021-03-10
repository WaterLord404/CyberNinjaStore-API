package com.cyberninja.model.entity.dto;

import com.cyberninja.model.entity.enumerated.DiscountType;

public class DiscountDTO {

	private Long id;

	private Double value;

	private DiscountType type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public DiscountType getType() {
		return type;
	}

	public void setType(DiscountType type) {
		this.type = type;
	}

}
