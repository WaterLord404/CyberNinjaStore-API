package com.cyberninja.model.entity.dto;

import java.util.List;
import java.util.Set;

import com.cyberninja.model.entity.enun.ProductCategory;
import com.cyberninja.model.entity.enun.ProductColour;
import com.cyberninja.model.entity.enun.ProductSize;

public class ProductDTO {

	private Long id;
	
	private String name;

	private String description;

	private Double price;

	private Set<ProductSize> size;

	private Set<ProductColour> colour;

	private Set<ProductCategory> category;
	
	private List<DocumentDTO> documents;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Set<ProductSize> getSize() {
		return size;
	}

	public void setSize(Set<ProductSize> size) {
		this.size = size;
	}

	public Set<ProductColour> getColour() {
		return colour;
	}

	public void setColour(Set<ProductColour> colour) {
		this.colour = colour;
	}

	public Set<ProductCategory> getCategory() {
		return category;
	}

	public void setCategory(Set<ProductCategory> category) {
		this.category = category;
	}

	public List<DocumentDTO> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentDTO> documents) {
		this.documents = documents;
	}

}
