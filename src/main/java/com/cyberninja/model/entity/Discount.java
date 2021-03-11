package com.cyberninja.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cyberninja.model.entity.enumerated.DiscountType;

@Entity
@Table(name = "DISCOUNTS")
public class Discount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8892199960050575645L;

	private Long id;

	private Double value;

	private DiscountType type;

	private LocalDate creationDate;

	private Boolean active;

	private List<Coupon> coupons;

	private List<Product> products;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DISCOUNT_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "VALUE", nullable = false)
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Column(name = "TYPE", nullable = false)
	public DiscountType getType() {
		return type;
	}

	public void setType(DiscountType type) {
		this.type = type;
	}

	@Column(name = "CREATION_DATE", nullable = false)
	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "ACTIVE", nullable = false)
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@OneToMany(mappedBy = "discount", cascade = CascadeType.ALL)
	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	@OneToMany(mappedBy = "discount", cascade = CascadeType.ALL)
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
