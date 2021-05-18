package com.cyberninja.model.entity.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class OrderDTO {

	private Long id;

	private Double totalPrice;

	private LocalDate purchaseDate;

	private List<OrderDetailsDTO> ordersDetails;

	private CouponDTO coupon;

	private ShippingDTO shipping;

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

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}


	public CouponDTO getCoupon() {
		return coupon;
	}

	public void setCoupon(CouponDTO coupon) {
		this.coupon = coupon;
	}

	public ShippingDTO getShipping() {
		return shipping;
	}

	public void setShipping(ShippingDTO shipping) {
		this.shipping = shipping;
	}

	public List<OrderDetailsDTO> getOrdersDetails() {
		return ordersDetails;
	}

	public void setOrdersDetails(List<OrderDetailsDTO> ordersDetails) {
		this.ordersDetails = ordersDetails;
	}

}
