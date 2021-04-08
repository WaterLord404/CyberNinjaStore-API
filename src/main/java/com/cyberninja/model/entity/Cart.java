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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CARTS")
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6863401092736642080L;

	private Long id;

	private LocalDate updateDate;

	private Customer customer;

	private List<OrderDetails> ordersDetails;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CART_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "UPDATE_DATE")
	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}

	@OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@OneToMany(mappedBy = "cart", orphanRemoval = true, cascade = CascadeType.ALL)
	public List<OrderDetails> getOrdersDetails() {
		return ordersDetails;
	}

	public void setOrdersDetails(List<OrderDetails> ordersDetails) {
		this.ordersDetails = ordersDetails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
