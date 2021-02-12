package com.techshop.model.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTS")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4432182748990375268L;

	private Long id;

	private String name;

	private String description;

	private Double price;

	private Set<ProductSize> SIZE;

//	private Image image;

	private Set<ProductColour> COLOUR;

	private Set<ProductCategory> CATEGORY;

	private List<Order> order;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NAME", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION", nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "PRICE", nullable = false)
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@Column(name = "SIZE")
	public Set<ProductSize> getSIZE() {
		return SIZE;
	}

	public void setSIZE(Set<ProductSize> size) {
		SIZE = size;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@Column(name = "COLOUR")
	public Set<ProductColour> getCOLOUR() {
		return COLOUR;
	}

	public void setCOLOUR(Set<ProductColour> colour) {
		COLOUR = colour;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@Column(name = "CATEGORY")
	public Set<ProductCategory> getCATEGORY() {
		return CATEGORY;
	}

	public void setCATEGORY(Set<ProductCategory> category) {
		CATEGORY = category;
	}

	@ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
