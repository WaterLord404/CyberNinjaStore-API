package com.cyberninja.model.entity.dto;

import com.cyberninja.model.entity.enumerated.ProductColour;
import com.cyberninja.model.entity.enumerated.ProductSize;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class OrderDetailsDTO {

	private Integer units;

	private ProductColour colour;

	private ProductSize size;

	private ProductDTO product;

	private OrderDTO order;

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public ProductColour getColour() {
		return colour;
	}

	public void setColour(ProductColour color) {
		this.colour = color;
	}

	public ProductSize getSize() {
		return size;
	}

	public void setSize(ProductSize size) {
		this.size = size;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public OrderDTO getOrder() {
		return order;
	}

	public void setOrder(OrderDTO order) {
		this.order = order;
	}

}
