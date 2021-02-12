package com.techshop.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7311370312609110974L;

	private Long id;

	private Double totalPrice;

	private LocalDate purchaseTime;

	private Customer customer;

	private List<Product> products;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "TOTAL_PRICE", nullable = false)
	public Double getPrice() {
		return totalPrice;
	}

	public void setPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "PURCHASE_DATE", nullable = false)
	public LocalDate getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(LocalDate purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_ORDER__CUSTOMER_ID"))
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@JoinTable(name = "REL_ORDER_PRODUCT", joinColumns = @JoinColumn(name = "ORDER_ID", nullable = false), 
									 	   inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID", nullable = false),
									 	   foreignKey = @ForeignKey(name = "FK_ORDER__PRODUCT_ID"), 
									 	   inverseForeignKey = @ForeignKey(name = "FK_PRODUCT__ORDER_ID"))
	@ManyToMany(cascade = CascadeType.ALL)
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
