package com.cyberninja.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COUPONS")
public class Coupon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8892199960050575645L;

	private Long id;

	private String code;

	private LocalDate creationDate;

	private LocalDate expirationDate;

	private Integer uses;

	private Integer maxUses;

	private Boolean active;

	private Discount discount;

	private Order order;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COUPONS_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "CODE", unique = true, nullable = false)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "CREATION_DATE")
	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "EXPIRATION_DATE")
	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Column(name = "USES")
	public Integer getUses() {
		return uses;
	}

	public void setUses(Integer uses) {
		this.uses = uses;
	}

	@Column(name = "MAX_USES")
	public Integer getMaxUses() {
		return maxUses;
	}

	public void setMaxUses(Integer maxUses) {
		this.maxUses = maxUses;
	}

	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DISCOUNT_ID", foreignKey = @ForeignKey(name = "FK_COUPONS__DISCOUNT_ID"))
	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	@OneToOne(mappedBy = "coupon", cascade = CascadeType.ALL)
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
