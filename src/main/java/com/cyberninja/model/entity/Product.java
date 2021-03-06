package com.cyberninja.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cyberninja.model.entity.enun.ProductCategory;
import com.cyberninja.model.entity.enun.ProductColour;
import com.cyberninja.model.entity.enun.ProductSize;

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

	private Double purchasePrice;

	private Double salePrice;

	private Double priceWoutDiscount;

	private Double totalPrice;

	private Double discount;

	private LocalDate createTime;

	private Set<ProductSize> size;

	private Set<ProductColour> colour;

	private Set<ProductCategory> category;

	private List<Document> documents;

	private List<Order> order;

	private boolean active;

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

	@Lob
	@Column(name = "DESCRIPTION", nullable = false, length = 1000)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "PURCHASE_PRICE", nullable = false)
	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	@Column(name = "SALE_PRICE", nullable = false)
	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	@Column(name = "PRICE_WOUT_DISCOUNT", nullable = false)
	public Double getPriceWoutDiscount() {
		return priceWoutDiscount;
	}

	public void setPriceWoutDiscount(Double priceWoutDiscount) {
		this.priceWoutDiscount = priceWoutDiscount;
	}

	@Column(name = "TOTAL_PRICE", nullable = false)
	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "DISCOUNT", nullable = false)
	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	@Column(name = "CREATE_TIME")
	public LocalDate getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDate createTime) {
		this.createTime = createTime;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@Column(name = "SIZE", nullable = false)
	public Set<ProductSize> getSize() {
		return size;
	}

	public void setSize(Set<ProductSize> size) {
		this.size = size;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@Column(name = "COLOUR", nullable = false)
	public Set<ProductColour> getColour() {
		return colour;
	}

	public void setColour(Set<ProductColour> colour) {
		this.colour = colour;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@Column(name = "CATEGORY", nullable = false)
	public Set<ProductCategory> getCategory() {
		return category;
	}

	public void setCategory(Set<ProductCategory> category) {
		this.category = category;
	}

	@OneToMany(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.ALL)
	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	@ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	@Column(name = "ACTIVE", nullable = false)
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
