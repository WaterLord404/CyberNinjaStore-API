package com.cyberninja.model.entity.dto;

import java.util.Date;

import com.cyberninja.model.entity.enumerated.ShippingStatus;

public class ShippingDTO {

	private Long id;

	private String uuid;

	private Date updateDate;

	private String village;

	private String county;

	private String state;

	private ShippingStatus status;

	private CustomerDTO customer;

	public Long getId() {
		return id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public ShippingStatus getStatus() {
		return status;
	}

	public void setStatus(ShippingStatus status) {
		this.status = status;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

}
