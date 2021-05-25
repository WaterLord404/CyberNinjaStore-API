package com.cyberninja.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cyberninja.model.entity.enumerated.ShippingStatus;
import com.cyberninja.security.model.entity.User;

@Entity
@Table(name = "SHIPPINGS")
public class Shipping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9117726407920940329L;

	private Long id;

	private String uuid;

	private Date updateDate;

	private String village;

	private String county;

	private String state;

	private ShippingStatus status;

	private Order order;

	private User user;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SHIPPING_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "UUID", nullable = false)
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name = "UPDATE_DATE")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", nullable = false)
	public ShippingStatus getStatus() {
		return status;
	}

	public void setStatus(ShippingStatus status) {
		this.status = status;
	}

	@OneToOne(mappedBy = "shipping", cascade = CascadeType.ALL)
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Column(name = "VILLAGE")
	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	@Column(name = "COUNTRY")
	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	@Column(name = "STATE")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@OneToOne
	@JoinColumn(name = "USER_SHIPPER_ID", foreignKey = @ForeignKey(name = "FK_SHIPPING__USER_SHIPPER_ID"))
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
