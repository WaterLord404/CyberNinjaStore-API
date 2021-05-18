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
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cyberninja.model.entity.enumerated.ProductCategory;
import com.cyberninja.model.entity.enumerated.ProductColour;
import com.cyberninja.model.entity.enumerated.ProductSize;

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

	private Double priceWithVat;

	private Double totalPrice;

	private LocalDate creationDate;

	private Double stars;
	
	private Set<ProductSize> size;

	private Set<ProductColour> colour;

	private Set<ProductCategory> category;

	private Discount discount;

	private List<Document> documents;

	private List<OrderDetails> ordersDetails;

	private boolean active;

	private Provider provider;

	private List<Review> reviews;

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

	@Column(name = "PRICE_WITH_VAT", nullable = false)
	public Double getPriceWithVat() {
		return priceWithVat;
	}

	public void setPriceWithVat(Double priceWithVat) {
		this.priceWithVat = priceWithVat;
	}

	@Column(name = "TOTAL_PRICE", nullable = false)
	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "CREATION_TIME")
	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@Column(name = "SIZE", nullable = false)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_PRODUCTS_SIZE___PRODUCT_ID"))
	public Set<ProductSize> getSize() {
		return size;
	}

	public void setSize(Set<ProductSize> size) {
		this.size = size;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@Column(name = "COLOUR", nullable = false)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_PRODUCTS_COLOUR___PRODUCT_ID"))
	public Set<ProductColour> getColour() {
		return colour;
	}

	public void setColour(Set<ProductColour> colour) {
		this.colour = colour;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@Column(name = "CATEGORY", nullable = false)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_PRODUCTS_CATEGORY___PRODUCT_ID"))
	public Set<ProductCategory> getCategory() {
		return category;
	}

	public void setCategory(Set<ProductCategory> category) {
		this.category = category;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DISCOUNT_ID", foreignKey = @ForeignKey(name = "FK_PRODUCTS__DISCOUNT_ID"))
	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	public List<OrderDetails> getOrdersDetails() {
		return ordersDetails;
	}

	public void setOrdersDetails(List<OrderDetails> ordersDetails) {
		this.ordersDetails = ordersDetails;
	}

	@Column(name = "ACTIVE", nullable = false)
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PROVIDER_ID", foreignKey = @ForeignKey(name = "FK_PRODUCTS__PROVIDER_ID"))
	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	@Column(name = "STARS", nullable = false)
	public Double getStars() {
		return stars;
	}

	public void setStars(Double stars) {
		this.stars = stars;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
