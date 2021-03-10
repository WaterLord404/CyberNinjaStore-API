package com.cyberninja.model;

import java.io.Serializable;

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
@Table(name = "ORDERS_DETAILS")
public class OrderDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7554941645655995147L;

	private Long id;

	private Integer units;

	private String color;

	private String size;

	private Product product;

	private Order order;

	private Coupon coupon;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_DETAILS_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "UNITS", nullable = false)
	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	@Column(name = "COLOR", nullable = false)
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Column(name = "SIZE", nullable = false)
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PRODUCT_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_ORDERS_DETAILS__PRODUCT_ID"))
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ORDER_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_ORDERS_DETAILS__ORDER_ID"))
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@OneToOne
	@JoinColumn(name = "CUPON_ID", foreignKey = @ForeignKey(name = "FK_ORDERS_DETAILS__CUPON_ID"))
	public Coupon getCupon() {
		return coupon;
	}

	public void setCupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
