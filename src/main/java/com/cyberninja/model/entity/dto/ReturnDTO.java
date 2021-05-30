package com.cyberninja.model.entity.dto;

public class ReturnDTO {

	private ShippingDTO shipping;

	private OrderDetailsDTO orderDetails;

	public ShippingDTO getShipping() {
		return shipping;
	}

	public void setShipping(ShippingDTO shipping) {
		this.shipping = shipping;
	}

	public OrderDetailsDTO getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(OrderDetailsDTO orderDetails) {
		this.orderDetails = orderDetails;
	}

}
