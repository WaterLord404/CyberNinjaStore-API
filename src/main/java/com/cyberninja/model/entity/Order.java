package com.cyberninja.model.entity;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	private LocalDate purchaseDate;

	private Customer customer;

	private List<OrderDetails> ordersDetails;

	private Coupon coupon;

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
	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "PURCHASE_DATE", nullable = false)
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_ORDERS__CUSTOMER_ID"))
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@OneToMany(mappedBy = "order", orphanRemoval = true, cascade = CascadeType.ALL)
	public List<OrderDetails> getOrdersDetails() {
		return ordersDetails;
	}

	public void setOrdersDetails(List<OrderDetails> ordersDetails) {
		this.ordersDetails = ordersDetails;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "COUPON_ID", foreignKey = @ForeignKey(name = "FK_ORDERS__COUPON_ID"))
	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
