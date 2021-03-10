package com.cyberninja.model.dto;

import java.util.List;
import java.util.Set;

import com.cyberninja.model.enumerated.ProductCategory;
import com.cyberninja.model.enumerated.ProductColour;
import com.cyberninja.model.enumerated.ProductSize;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class ProductDTO {

	private Long id;

	private String name;

	private String description;

	private Double purchasePrice;

	private Double salePrice;

	private Double priceWoutDiscount;

	private Double totalPrice;

	private Double discount;

	private Set<ProductSize> size;

	private Set<ProductColour> colour;

	private Set<ProductCategory> category;

	private List<DocumentDTO> documents;

	private boolean active;

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

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getPriceWoutDiscount() {
		return priceWoutDiscount;
	}

	public void setPriceWoutDiscount(Double priceWoutDiscount) {
		this.priceWoutDiscount = priceWoutDiscount;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
