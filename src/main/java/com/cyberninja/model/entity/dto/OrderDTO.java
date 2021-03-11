package com.cyberninja.model.entity.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class OrderDTO {

	private Long id;

	private Double totalPrice;

	private List<OrderDetailsDTO> orderProducts;

	private CouponDTO coupon;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<OrderDetailsDTO> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProductDTO(List<OrderDetailsDTO> orderProducts) {
		this.orderProducts = orderProducts;
	}

	public CouponDTO getCoupon() {
		return coupon;
	}

	public void setCoupon(CouponDTO coupon) {
		this.coupon = coupon;
	}

	public void setOrderProducts(List<OrderDetailsDTO> orderProducts) {
		this.orderProducts = orderProducts;
	}

}
